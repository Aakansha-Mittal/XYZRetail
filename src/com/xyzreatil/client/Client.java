package com.xyzreatil.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.xyzreatil.exception.AuthenticationException;
import com.xyzreatil.presentation.RetailPresentation;
import com.xyzreatil.presentation.RetailPresentationImpl;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		RetailPresentation retailPresentation = new RetailPresentationImpl();
		try {
			if (retailPresentation.loginPage()) {
				while (true) {
					retailPresentation.showMenu();
					System.out.println("Enter Choice : ");
					int choice = scanner.nextInt();
					retailPresentation.performMenu(choice);
				}
			}
		} catch (AuthenticationException e) {
			System.out.println("Login failed: " + e.getMessage());
		}
	}

}