package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qa.ims.persistence.domain.OrderItem;

public interface UpdateDAO<T> {
	
	List<T> view(long id);

	T add(T t);
	
	double sum(long id);

	int remove(long id, long itemID);
	
	T modelFromResultSet(ResultSet resultSet) throws SQLException;
}
