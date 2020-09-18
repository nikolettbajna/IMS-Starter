package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.UpdateAction;
import com.qa.ims.controller.UpdateItemController;
import com.qa.ims.controller.UpdateController;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.UpdateOrderDAO;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customers;
	private final ItemController items;
	private final OrderController orders;
	private final UpdateItemController orderitems;
	private final Utils utils;

	public IMS() {
		this.utils = new Utils();
		final CustomerDAO custDAO = new CustomerDAO();
		this.customers = new CustomerController(custDAO, utils);
		final ItemDAO itemDAO = new ItemDAO();
		this.items = new ItemController(itemDAO, utils);
		final OrderDAO orderDAO = new OrderDAO();
		this.orders = new OrderController(orderDAO, utils);
		final UpdateOrderDAO updateDAO = new UpdateOrderDAO();
		this.orderitems = new UpdateItemController(updateDAO, utils);
	}

	public void imsSystem() {
		LOGGER.info("Welcome to the Inventory Management System!");
		DBUtils.connect("src/main/resources/db.properties");
		DBUtils.getInstance().init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");

		Domain domain = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			}else{
				doAction(active, action, domain.name().toLowerCase());
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action, String domain) {
		boolean changeAction = false;
		do {
			switch (action) {
			case CREATE:
				crudController.create();
				break;
			case READ:
				crudController.readAll();
				break;
	//		case FIND:		
	//			break;
			case UPDATE:
				if (domain.equals("order")) {
					LOGGER.info("Which action would you like to perform:");
					UpdateAction.printActions();
					UpdateAction updateAction = UpdateAction.getAction(utils);
					UpdateController<?> uc = this.orderitems;
					if (updateAction == UpdateAction.BACK) {
						changeAction = true;
					}else{
						doUpdateAction(uc, updateAction);
					}
				}else {
					crudController.update();
				}
				break;
			case DELETE:
				crudController.delete();
				break;
			case RETURN:
				break;
			default:
				break;
			}
		}while(!changeAction);
	}
	
	public void doUpdateAction(UpdateController<?> updateController, UpdateAction action) {
		switch (action) {
		case ADD:
			updateController.add();
			break;
		case VIEW:
			updateController.view();
			break;
		case REMOVE:
			updateController.remove();
			break;
		case SUM:
			updateController.sum();
			break;
		case BACK:
			break;
		default:
			break;
		}
	}
	
//	public void findAction(CrudController<?> crudController, Action action) {
//		boolean changeAction = false;
//		do {
//
//			CrudController<?> active = null;
//			switch(action) {
//			case BYNAME:
//				System.out.println("By name");
//				break;
//			case BYID:
//				System.out.println("By id");
//				break;
//			case LAST:
//				System.out.println("last");
//				break;
//			case BACK:
//				break;
//			default:
//				break;
//			}
//
//			LOGGER.info("What would you like to do with " + action.name().toLowerCase() + ":");
//
//			Action.printActions();
//			Action a = Action.getAction(utils);
//
//			if (a == Action.BACK) {
//				changeAction = true;
//			} else {
//				doAction(active, a);
//			}
//		} while (!changeAction);
//	}

}
