package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.UpdateOrderDAO;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

/**
 * Takes in Order details for CRUD functionality
 *
 */
public class UpdateItemController implements UpdateController<OrderItem> {

	public static final Logger LOGGER = LogManager.getLogger();

	private UpdateOrderDAO UpdateOrderDAO;
	private Utils utils;

	public UpdateItemController(UpdateOrderDAO UpdateOrderDAO, Utils utils) {
		super();
		this.UpdateOrderDAO = UpdateOrderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items in order
	 */
	@Override
	public List<OrderItem> view() {
		LOGGER.info("Please enter order id");
		Long orderID = utils.getLong();
		List<OrderItem> orderItem = UpdateOrderDAO.view(orderID);
		for (OrderItem OrderItem : orderItem) {
			LOGGER.info(OrderItem.toString());
		}
		return orderItem;
	}

	/**
	 * Add item to order by taking in user input
	 */
	@Override
	public OrderItem add() {
		LOGGER.info("Please enter order id");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter item id");
		Long itemID = utils.getLong();
		OrderItem orderItem = UpdateOrderDAO.add(new OrderItem(itemID, orderID));
		LOGGER.info("Item added to order\n" + UpdateOrderDAO.readLatest());
		return orderItem;
	}

	/**
	 * Deletes an existing item from Order by the id of the Order
	 * 
	 * @return
	 */
	@Override
	public int remove() {
		LOGGER.info("Please enter the id of the Order you would like to delete from");
		Long id = utils.getLong();
		LOGGER.info("Please enter the id of the ordered item you would like to delete");
		Long itemID = utils.getLong();
		LOGGER.info("Item deleted from order (ID: " + id + ")");
		return UpdateOrderDAO.remove(id, itemID);
	}
	
	/**
	 * Calculate the total price of the order
	 * 
	 * @return
	 */
	@Override
	public double sum() {
		LOGGER.info("Please enter order id");
		Long orderID = utils.getLong();
		//OrderItem orderItem = UpdateOrderDAO.add(new OrderItem(orderID));
		LOGGER.info("");
		return UpdateOrderDAO.sum(orderID);
	}

}