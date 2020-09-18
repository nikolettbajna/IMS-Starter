package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String itemName;
	private String itemCategory;
	private double price;

	public Item(String itemName, String itemCategory, double price) {
		this.setItemName(itemName);
		this.setPrice(price);
		this.setCategory(itemCategory);
	}

	public Item(Long id, String itemName, String itemCategory, double price) {
		this.setId(id);
		this.setItemName(itemName);
		this.setPrice(price);
		this.setCategory(itemCategory);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCategory() {
		return itemCategory;
	}

	public void setCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	@Override
	public String toString() {
		return "ID: " + id + " Item: " + itemName + " Category: " + itemCategory + " Price: £" + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
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
		Item other = (Item) obj;
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
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
