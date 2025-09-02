package com.xyzreatil.presentation;

import java.sql.SQLException;

import com.xyzreatil.exception.AuthenticationException;

public interface RetailPresentation {
	
	boolean loginPage() ;
    void showMenu();
    void performMenu(int choice);
}
