package com.xyzreatil.persistence;

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

public interface RetailDAO {
	
	int validateCustomer(String username, String password) throws AuthenticationException;
	
	ArrayList<Item> fetchAllItems();
	
    ArrayList<Item> fetchItemsByCategory(String categoryName) throws CategoryNotFoundException;
    
    void insertItemToCart(int customerId, int itemId, int quantity) throws ItemNotFoundException, OutOfStockException, NegativeQuantityException;
    
    void deleteItemFromCart(int customerId, int itemId) throws ItemNotFoundException;
    
    ArrayList<CartItem> fetchCart(int customerId) throws CartNotFoundException;
    
    Bill saveTransaction(Transaction transaction, ArrayList<TransactionItem> transactionItems);
    
}