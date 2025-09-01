package com.xyzreatil.entity;

import java.time.LocalDateTime;

public class Transaction {
	
	private int transactionId;
    private int customerId;
    private double totalAmount;
    private LocalDateTime timestamp;
    
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Transaction(int transactionId, int customerId, double totalAmount, LocalDateTime timestamp) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
		this.timestamp = timestamp;
	}
	public Transaction() {

	}
    
	
    


}
