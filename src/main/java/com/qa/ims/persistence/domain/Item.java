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
		Double prc = new Double(price);
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prc == null) ? 0 : prc.hashCode());
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
		if (getItemName() == null) {
			if (other.getItemName() != null)
				return false;
		} else if (!getItemName().equals(other.getItemName()))
			return false;
		if (getCategory() == null) {
			if (other.getCategory() != null)
				return false;
		} else if (!getCategory().equals(other.getCategory()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == 0) {
			if (other.price != 0)
				return false;
		} else if (price !=other.price )
			return false;
		return true;
	}

}
