package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.UpdateItemController;
import com.qa.ims.persistence.dao.UpdateOrderDAO;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class UpdateItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private UpdateOrderDAO dao;

	@InjectMocks
	private UpdateItemController controller;
	
	@Mock
	private java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	
	@Mock
	private Date date = new Date(); 

	@Test
	public void testAdd() {
		final Long ID = 5L, ORDERID = 1L, ITEMID = 3L;
		final String ITEMNAME = "teddy bear", CATEGORY = "toy";
		final double PRICE = 11.99;
		final OrderItem added = new OrderItem(ID, ORDERID, ITEMID, ITEMNAME, CATEGORY, PRICE);

		Mockito.when(utils.getLong()).thenReturn(ID, ORDERID, ITEMID);
		Mockito.when(utils.getString()).thenReturn(ITEMNAME, CATEGORY);
		Mockito.when(utils.getDouble()).thenReturn(PRICE);
		Mockito.when(dao.add(added)).thenReturn(added);

		assertEquals(added, controller.add());

		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).add(added);
	}

	@Test
	public void testView() {
		List<OrderItem> orderItem = new ArrayList<>();
		orderItem.add(new OrderItem(1L, 1L, 1L, "ball", "equipment", 2.33));

		Mockito.when(dao.view(1)).thenReturn(orderItem);

		assertEquals(orderItem, controller.view());

		Mockito.verify(dao, Mockito.times(1)).view(1);
	}

	@Test
	public void testRemove() {
		final long ID = 1L;
		final long IID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID, IID);
		Mockito.when(dao.remove(ID, IID)).thenReturn(1, 1);

		assertEquals(1L, this.controller.remove());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).remove(ID, IID);
	}

}