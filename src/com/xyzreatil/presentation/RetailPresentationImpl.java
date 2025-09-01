package com.xyzreatil.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.xyzreatil.entity.Bill;
import com.xyzreatil.entity.CartItem;
import com.xyzreatil.entity.Item;
import com.xyzreatil.exception.AuthenticationException;
import com.xyzreatil.exception.CartNotFoundException;
import com.xyzreatil.exception.CategoryNotFoundException;
import com.xyzreatil.exception.ItemNotFoundException;
import com.xyzreatil.exception.OutOfStockException;
import com.xyzreatil.service.RetailServiceImpl;
import com.xyzreatil.service.RetailService;

public class RetailPresentationImpl implements RetailPresentation {

	private RetailService service = new RetailServiceImpl();
	private String loggedInUser;

	@Override
	public boolean loginPage() throws AuthenticationException {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		if (service.login(username, password) != 0) {
			System.out.println("Login successful! Welcome " + username);
			loggedInUser = username;
			return true;
		} else {
			throw new AuthenticationException("Invalid username or password");
		}
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. View All Items");
		System.out.println("2. Search By Category");
		System.out.println("3. Add Item To Cart");
		System.out.println("4. Remove Item From Cart");
		System.out.println("5. View Cart");
		System.out.println("6. Checkout");
		System.out.println("7. Exit");

	}

	@Override
	public void performMenu(int choice) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		switch (choice) {
		case 1:
			ArrayList<Item> items = service.viewAllItems();
			items.forEach(System.out::println);
			break;
		case 2:
			System.out.println("Enter category:");
			String cat = sc.nextLine();
			try {
				ArrayList<Item> catItems = service.searchByCategory(cat);
				catItems.forEach(System.out::println);
			} catch (CategoryNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			System.out.println("Enter itemId and quantity:");
			int id = sc.nextInt();
			int qty = sc.nextInt();
			try {
				service.addItemToCart(loggedInUser, id, qty);
			} catch (ItemNotFoundException e) {
				System.out.println("Item not found!!");
			} catch (OutOfStockException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			System.out.println("Enter itemId to remove:");
			int removeId = sc.nextInt();
			try {
				service.removeItemFromCart(loggedInUser, removeId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			try {
				ArrayList<CartItem> cart = service.viewCart(loggedInUser);
				cart.forEach(System.out::println);
			} catch (CartNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 6:
			try {
				Bill bill = service.checkoutAndGenerateBill(loggedInUser);
				System.out.println("Bill Generated: " + bill);
			} catch (CartNotFoundException e) {
				System.out.println("Cart is empty, cannot checkout!");
			}
			break;
		case 7:
			System.out.println("Thank you!");
			System.exit(0);
		default:
			System.out.println("Invalid choice!");
		}

	}

}
