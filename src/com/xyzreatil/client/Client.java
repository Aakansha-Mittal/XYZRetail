package com.xyzreatil.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.xyzreatil.exception.AuthenticationException;
import com.xyzreatil.presentation.RetailPresentation;
import com.xyzreatil.presentation.RetailPresentationImpl;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int wrongCount =0;
		Scanner scanner = new Scanner(System.in);

		RetailPresentation retailPresentation = new RetailPresentationImpl();
		try {
			while (wrongCount <3 ) {
			if (retailPresentation.loginPage()) {
				while (true) {
					retailPresentation.showMenu();
					System.out.println("Enter Choice : ");
					int choice = scanner.nextInt();
					retailPresentation.performMenu(choice);
				}
			}
			}
		}
		catch (AuthenticationException e) {
			System.out.println("\nERROR MESSAGE : INVALID USERNAME OR PASSWORD.\n");
			wrongCount++;
		}
	}

}