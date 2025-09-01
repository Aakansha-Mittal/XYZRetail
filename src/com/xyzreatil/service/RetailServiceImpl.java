package com.xyzreatil.service;

import java.sql.SQLException;
import java.util.ArrayList;

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
import com.xyzreatil.persistence.RetailDAO;
import com.xyzreatil.persistence.RetailDAOImpl;

public class RetailServiceImpl implements RetailService {

	private RetailDAO retailDAOImpl = new RetailDAOImpl();
	private int customerId = 0;

	@Override
	public int login(String username, String password) throws AuthenticationException {
		customerId =  retailDAOImpl.validateCustomer(username, password);
		return customerId;
	}

	@Override
	public ArrayList<Item> viewAllItems() {
		return retailDAOImpl.fetchAllItems();
	}

	@Override
	public ArrayList<Item> searchByCategory(String categoryName) throws CategoryNotFoundException {
		return retailDAOImpl.fetchItemsByCategory(categoryName);
	}

	@Override
	public void addItemToCart(String username, int itemId, int quantity) 
			throws ItemNotFoundException, OutOfStockException, NegativeQuantityException  {
		//int customerId = getCustomerId(username);
		retailDAOImpl.insertItemToCart(customerId, itemId, quantity);
	}

	@Override
	public void removeItemFromCart(String username, int itemId) throws ItemNotFoundException {
		//int customerId = getCustomerId(username);
		retailDAOImpl.deleteItemFromCart(customerId, itemId);
	}

	@Override
	public ArrayList<CartItem> viewCart(String username) throws CartNotFoundException {
		//int customerId = getCustomerId(username);
		return retailDAOImpl.fetchCart(customerId);
	}

	@Override
	public Bill checkoutAndGenerateBill(String username) throws CartNotFoundException {
		//int customerId = getCustomerId(username);

		ArrayList<CartItem> cart = retailDAOImpl.fetchCart(customerId);

		double totalAmount = 0;
		ArrayList<TransactionItem> tItems = new ArrayList<>();
		for (CartItem ci : cart) {
			tItems.add(new TransactionItem(0, 0, ci.getItemId(), ci.getQuantity()));
		}
		Transaction transaction = new Transaction(0, customerId, totalAmount, null);
		return retailDAOImpl.saveTransaction(transaction, tItems);

	}
}