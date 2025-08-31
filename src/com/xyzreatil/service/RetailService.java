package com.xyzreatil.service;

import java.util.ArrayList;

import com.xyzreatil.entity.Bill;
import com.xyzreatil.entity.Items;
import com.xyzreatil.exception.AuthenticationException;
import com.xyzreatil.exception.ItemNotFoundException;
import com.xyzreatil.exception.OutOfStockException;

public interface RetailService {
	
	boolean authenticate(String username, String password) throws AuthenticationException;
    ArrayList<Items> viewAllItems();
    ArrayList<Items> searchByCategory(String categoryName);
    void addItemToBasket(int itemId, int qty) throws ItemNotFoundException, OutOfStockException;
    void removeItemFromBasket(int itemId) throws ItemNotFoundException;
    Bill checkout();
	

}

