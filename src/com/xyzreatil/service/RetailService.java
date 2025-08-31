package com.xyzreatil.service;

import java.util.ArrayList;

import com.xyzreatil.entity.Bill;
import com.xyzreatil.entity.CartItem;
import com.xyzreatil.entity.Item;
import com.xyzreatil.exception.AuthenticationException;
import com.xyzreatil.exception.CartNotFoundException;
import com.xyzreatil.exception.CategoryNotFoundException;
import com.xyzreatil.exception.ItemNotFoundException;
import com.xyzreatil.exception.OutOfStockException;

public interface RetailService {
	
	boolean login(String username, String password) throws AuthenticationException;
	
    ArrayList<Item> viewAllItems();
    
    ArrayList<Item> searchByCategory(String categoryName) throws CategoryNotFoundException;
    
    void addItemToCart(String username, int itemId, int quantity) throws ItemNotFoundException, OutOfStockException;
    
    void removeItemFromCart(String username, int itemId) throws ItemNotFoundException;
    
    //ArrayList<Item> viewCart(String username) throws CartNotFoundException;
    
    ArrayList<CartItem> viewCart(String username) throws CartNotFoundException;
    
    Bill checkoutAndGenerateBill(String userName);
	

}

