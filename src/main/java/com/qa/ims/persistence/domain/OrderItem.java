package com.qa.ims.persistence.domain;

public class OrderItem {
	
	private Long id, itemID, orderID;
	private String itemName;
	private String itemCategory;
	private double price, totalPrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemID() {
		return itemID;
	}
	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public OrderItem(Long orderID) {
		this.setOrderID(orderID);
	}
	
	public OrderItem(double totalPrice) {
		this.setTotalPrice(totalPrice);
	}
	
	public OrderItem(Long itemID, Long orderID) {
		this.setOrderID(orderID);
		this.setItemID(itemID);
	}
	
	public OrderItem(Long id, Long orderID, Long itemID) {
		this.setId(id);
		this.setOrderID(orderID);
		this.setItemID(itemID);
	}
	
	public OrderItem(Long id, Long orderID, Long itemID, String itemName, String category, double price) {
		this.setId(id);
		this.setOrderID(orderID);
		this.setItemID(itemID);
		this.setItemCategory(category);
		this.setPrice(price);
	}
	
	@Override
	public String toString() {
		return "Order id: " + orderID + ", Item id: " + itemID + ", Item: " + itemName
				+ ", Category: " + itemCategory + ", Price: £" + price + "\n\n";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemCategory == null) {
			if (other.itemCategory != null)
				return false;
		} else if (!itemCategory.equals(other.itemCategory))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
