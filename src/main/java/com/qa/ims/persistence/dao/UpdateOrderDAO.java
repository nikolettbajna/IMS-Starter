package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class UpdateOrderDAO implements UpdateDAO<OrderItem> {
	
public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("orderitem.id");
		Long itemID = resultSet.getLong("orderitem.itemID");
		Long orderID = resultSet.getLong("orderitem.orderID");
		String itemName = resultSet.getString("items.item_name");
		String category = resultSet.getString("items.category");
		double price = resultSet.getDouble("items.price");
		return new OrderItem(id, orderID, itemID, itemName, category, price);
	}

	/**
	 * Reads all Orders from the database
	 * 
	 * @return A list of Orders
	 */
	@Override
	public List<OrderItem> view(long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select orderitem.id, orderitem.orderID, orderitem.itemID, items.item_name, items.category, items.price FROM orderitem INNER JOIN items ON orderitem.itemID = items.id WHERE orderitem.orderID = " + orderID + " ORDER BY orderitem.id");) {
			List<OrderItem> orderItem = new ArrayList<>();
			while (resultSet.next()) {
				orderItem.add(modelFromResultSet(resultSet));
			}
			return orderItem;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrderItem readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select orderitem.id, orderitem.orderID, orderitem.itemID, items.item_name, items.category, items.price FROM orderitem INNER JOIN items ON orderitem.itemID = items.id ORDER BY orderitem.id DESC LIMIT 1");) {
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
	public OrderItem add(OrderItem order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orderitem(itemID, orderID) values(" + order.getItemID() + "," + order.getOrderID() + ")");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrderItem readOrder(Long id) {
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
	
	public double sum(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select sum(price) from items, orderitem where items.id = orderitem.itemID AND orderitem.orderID = " + id);) {
			resultSet.next();
			String sum = resultSet.getString(1);
			System.out.println("Order total: " + sum);
			double value = Double.parseDouble(sum);
			return value;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * Deletes a Order in the database
	 * 
	 * @param id - id of the Order
	 */
	@Override
	public int remove(long id, long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orderitem where orderId = " + id + " AND itemID = " + itemID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
