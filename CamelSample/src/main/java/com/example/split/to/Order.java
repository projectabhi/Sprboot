package com.example.split.to;

public class Order {
	private String itemName;
    private int quantity;
    private int price;
    private boolean processed;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	public Order(String itemName, int quantity, int price, boolean processed) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.processed = processed;
	}
	@Override
	public String toString() {
		return "Order [itemName=" + itemName + ", quantity=" + quantity + ", price=" + price + ", processed="
				+ processed + "]";
	}
    
}
