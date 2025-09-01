package com.xyzreatil.entity;

public class TransactionItem {
	private int transactionItemId;
    private int transactionId;
    private int itemId;
    private int quantity;
	public int getTransactionItemId() {
		return transactionItemId;
	}
	public void setTransactionItemId(int transactionItemId) {
		this.transactionItemId = transactionItemId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TransactionItem() {
		super();
	}
	public TransactionItem(int transactionItemId, int transactionId, int itemId, int quantity) {
		super();
		this.transactionItemId = transactionItemId;
		this.transactionId = transactionId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
    
    

}
