package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Long id = resultSet.getLong("orders.id");
		Long custID = resultSet.getLong("orders.customerID");
		String dateTime = sdf.format(resultSet.getDate("orders.dateTime"));
		String firstName = resultSet.getString("customers.first_name");
		String surname = resultSet.getString("customers.surname");
		String email = resultSet.getString("customers.email");
		return new Order(id, custID, firstName, surname, email, dateTime);
	}

	/**
	 * Reads all Orders from the database
	 * 
	 * @return A list of Orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select customers.id, orders.customerID, orders.id, customers.first_name, customers.surname, customers.email, orders.dateTime from customers INNER JOIN orders ON customers.id = orders.customerID ORDER BY orders.id");) {
			List<Order> Orders = new ArrayList<>();
			while (resultSet.next()) {
				Orders.add(modelFromResultSet(resultSet));
			}
			return Orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT customers.id, orders.customerID, orders.id, customers.first_name, customers.surname, customers.email, orders.dateTime from customers INNER JOIN orders ON customers.id = orders.customerID ORDER BY orders.id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a Order in the database
	 * 
	 * @param Order - takes in a Order object. id will be ignored
	 */
	@Override
	public Order create(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO Orders(customerID) values('" + Order.getCustID() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders where id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a Order in the database
	 * 
	 * @param Order - takes in a Order object, the id field will be used to
	 *                 update that Order in the database
	 * @return
	 */
	@Override
	public Order update(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update Orders set customerID ='" + Order.getCustID() + "' where id =" + Order.getId());
			return readOrder(Order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a Order in the database
	 * 
	 * @param id - id of the Order
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from Orders where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
