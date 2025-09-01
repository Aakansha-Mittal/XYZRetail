package com.xyzreatil.presentation;

import java.sql.SQLException;

import com.xyzreatil.exception.AuthenticationException;

public interface RetailPresentation {
	
	boolean loginPage() throws AuthenticationException;
    void showMenu();
    void performMenu(int choice);
}
