package com.xyzreatil.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bill {
	private int billId;                
    private String customerUsername;   
    private LocalDateTime billDate;    
    private ArrayList<CartItem> items;      
    private double totalTax;          
    private double totalAmount;
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public LocalDateTime getBillDate() {
		return billDate;
	}
	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Bill(int billId, String customerUsername, LocalDateTime billDate, ArrayList<CartItem> items, double totalTax,
			double totalAmount) {
		super();
		this.billId = billId;
		this.customerUsername = customerUsername;
		this.billDate = billDate;
		this.items = items;
		this.totalTax = totalTax;
		this.totalAmount = totalAmount;
	}
	public Bill() {
		
	}
	@Override
	public String toString() {
		return "Bill [billId=" + billId + "\n, customerUsername=" + customerUsername + "\n, billDate=" + billDate
				+ "\n, items=" + items + "\n, totalTax=" + totalTax + "\n, totalAmount=" + totalAmount + "]";
	}  
	
	
    
    

}
