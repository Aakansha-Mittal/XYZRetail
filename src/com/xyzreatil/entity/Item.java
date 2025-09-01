package com.xyzreatil.entity;

public class Item {
	private int itemId;
    private String itemName;
    private int quantity;       
    private double unitPrice;
    private String categoryName;
    private int categoryId;
    private double tax;
    
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public Item(int itemId, String itemName, int quantity, double unitPrice, String categoryName, int categoryId,
			double tax) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.tax = tax;
	}
    
    public Item() {
    	
    }
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", categoryName=" + categoryName + ", categoryId=" + categoryId + ", tax=" + tax + "]";
	}
	
    

}
