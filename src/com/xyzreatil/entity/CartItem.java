package com.xyzreatil.entity;

public class CartItem {
	private int cartId;
    private int customerId;
    private int itemId;
    private String itemName;
    public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	private int quantity;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	
	public CartItem(int cartId, int customerId, int itemId,  int quantity, String itemName) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	public CartItem() {
		super();
	}
	@Override
	public String toString() {
		return "CartItem [cartId=" + cartId + ", customerId=" + customerId + ", itemId=" + itemId + ", itemName="
				+ itemName + ", quantity=" + quantity + "]";
	}
	
	
    
	
    

}
