package com.qa.ims.persistence.domain;

public class Order {
	private Long id, custID;
	private String dateTime, firstName, surname, email;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Order(Long custID) {
		this.setCustID(custID);
	}

	public Order(Long custID, String dateTime) {
		this.setCustID(custID);
		this.setDateTime(dateTime);
	}

	public Order(Long id, Long custID, String dateTime) {
		this.setId(id);
		this.setCustID(custID);
		this.setDateTime(dateTime);
	}
	
	public Order(Long id, Long custID, String firstName, String surname, String email, String dateTime) {
		this.setId(id);
		this.setCustID(custID);
		this.setDateTime(dateTime);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setEmail(email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustID() {
		return custID;
	}

	public void setCustID(Long custID) {
		this.custID = custID;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	@Override
	public String toString() {
		return "Order ID: " + id + ", Customer ID:" + custID + ", Customer name:" + firstName + " " + surname + ", Email: " + email + ", Order date: " + dateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custID == null) ? 0 : custID.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Order other = (Order) obj;
		if (custID == null) {
			if (other.custID != null)
				return false;
		} else if (!custID.equals(other.custID))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
