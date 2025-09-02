package com.xyzreatil.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.sql.Timestamp;

import com.xyzreatil.entity.Bill;
import com.xyzreatil.entity.CartItem;
import com.xyzreatil.entity.Item;
import com.xyzreatil.entity.Transaction;
import com.xyzreatil.entity.TransactionItem;
import com.xyzreatil.exception.AuthenticationException;
import com.xyzreatil.exception.CartNotFoundException;
import com.xyzreatil.exception.CategoryNotFoundException;
import com.xyzreatil.exception.ItemNotFoundException;
import com.xyzreatil.exception.NegativeQuantityException;
import com.xyzreatil.exception.OutOfStockException;

public class RetailDAOImpl implements RetailDAO {

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xyzretail", "root", "admin");
	}

	@Override
	public int validateCustomer(String username, String password) throws AuthenticationException {
		try (Connection connection = getConnection()) {
			int id = 0;
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM customer WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("customerId");
			}
			return id;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Item> fetchAllItems() {
		ArrayList<Item> items = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM item");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				items.add(new Item(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("quantity"),
						rs.getDouble("unitPrice"), rs.getString("categoryName"), rs.getInt("categoryId"),
						rs.getDouble("tax")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public ArrayList<Item> fetchItemsByCategory(String categoryName) throws CategoryNotFoundException {
		ArrayList<Item> items = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM item WHERE categoryName=?");
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new CategoryNotFoundException("Category not found!");
			}
			items.add(new Item(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("quantity"),
					rs.getDouble("unitPrice"), rs.getString("categoryName"), rs.getInt("categoryId"),
					rs.getDouble("tax")));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public void insertItemToCart(int customerId, int itemId, int quantity)
	        throws ItemNotFoundException, OutOfStockException {
	    try (Connection connection = getConnection()) {

	        // 1. Check available stock
	        PreparedStatement ps = connection.prepareStatement("SELECT quantity, itemName FROM item WHERE itemId=?");
	        ps.setInt(1, itemId);
	        ResultSet rs = ps.executeQuery();

	        if (!rs.next())
	            throw new ItemNotFoundException("Item not found!");

	        int availableQty = rs.getInt("quantity");
	        String itemName = rs.getString("itemName");

	        if (availableQty < quantity)
	            throw new OutOfStockException("Not enough stock!");

	        // 2. Check if item already exists in cart
	        ps = connection.prepareStatement("SELECT quantity FROM cart WHERE customerId=? AND itemId=?");
	        ps.setInt(1, customerId);
	        ps.setInt(2, itemId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // If item exists, update quantity
	            int existingQty = rs.getInt("quantity");
	            ps = connection.prepareStatement("UPDATE cart SET quantity = ? WHERE customerId=? AND itemId=?");
	            ps.setInt(1, existingQty + quantity);
	            ps.setInt(2, customerId);
	            ps.setInt(3, itemId);
	            ps.executeUpdate();
	        } else {
	            // If item does not exist, insert new row
	            ps = connection.prepareStatement("INSERT INTO cart(customerId, itemId, quantity, itemName) VALUES(?,?,?,?)");
	            ps.setInt(1, customerId);
	            ps.setInt(2, itemId);
	            ps.setInt(3, quantity);
	            ps.setString(4, itemName);
	            ps.executeUpdate();
	        }

	        // 3. Reduce stock in item table
	        ps = connection.prepareStatement("UPDATE item SET quantity = quantity - ? WHERE itemId=?");
	        ps.setInt(1, quantity);
	        ps.setInt(2, itemId);
	        ps.executeUpdate();

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteItemFromCart(int customerId, int itemId) throws ItemNotFoundException {
	    try (Connection connection = getConnection()) {
	        // 1. Get the quantity of the item in the cart
	        PreparedStatement ps = connection.prepareStatement("SELECT quantity FROM cart WHERE customerId=? AND itemId=?");
	        ps.setInt(1, customerId);
	        ps.setInt(2, itemId);
	        ResultSet rs = ps.executeQuery();

	        if (!rs.next())
	            throw new ItemNotFoundException("Item not in cart");

	        int quantityInCart = rs.getInt("quantity");

	        // 2. Delete item from cart
	        ps = connection.prepareStatement("DELETE FROM cart WHERE customerId=? AND itemId=?");
	        ps.setInt(1, customerId);
	        ps.setInt(2, itemId);
	        ps.executeUpdate();

	        // 3. Increase stock in item table
	        ps = connection.prepareStatement("UPDATE item SET quantity = quantity + ? WHERE itemId=?");
	        ps.setInt(1, quantityInCart);
	        ps.setInt(2, itemId);
	        ps.executeUpdate();

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public ArrayList<CartItem> fetchCart(int customerId) throws CartNotFoundException {
		ArrayList<CartItem> cart = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM cart WHERE customerId=?");
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cart.add(new CartItem(rs.getInt("cartId"), rs.getInt("customerId"), rs.getInt("itemId"),
						rs.getInt("quantity"), rs.getString("itemName")));
			}
			if (cart.isEmpty())
				throw new CartNotFoundException("Cart is empty!");
		} catch (Exception e) {
			throw new CartNotFoundException("Unable to fetch cart");
		}
		return cart;
	}

	@Override
	public Bill saveTransaction(Transaction transaction, ArrayList<TransactionItem> transactionItems) {
		Bill bill = new Bill();
		try (Connection connection = getConnection()) {
			connection.setAutoCommit(false);

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO transaction(customerId, totalAmount, timestamp) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, transaction.getCustomerId());
			ps.setDouble(2, transaction.getTotalAmount());
			ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			int transactionId = 0;
			if (rs.next())
				transactionId = rs.getInt(1);

			for (TransactionItem tItem : transactionItems) {
				ps = connection
						.prepareStatement("INSERT INTO transactionItem(transactionId, itemId, quantity) VALUES(?,?,?)");
				ps.setInt(1, transactionId);
				ps.setInt(2, tItem.getItemId());
				ps.setInt(3, tItem.getQuantity());
				ps.executeUpdate();
			}

			ps = connection.prepareStatement("DELETE FROM cart WHERE customerId=?");
			ps.setInt(1, transaction.getCustomerId());
			ps.executeUpdate();

			connection.commit();

			bill.setBillId(transactionId);
			bill.setCustomerUsername("dummy");
			bill.setBillDate(LocalDateTime.now());
			bill.setTotalAmount(transaction.getTotalAmount());
			bill.setItems(new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bill;
	}

}
