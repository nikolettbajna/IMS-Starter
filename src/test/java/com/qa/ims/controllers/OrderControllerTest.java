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

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;
	
	@Mock
	private java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	
	@Mock
	private Date date = new Date(); 

	@Test
	public void testCreate() {
		final Long ORDERID = 1L;
		final Long CUSTID = 2L;
		final String FNAME = "nikolett", SNAME = "bajna", EMAIL = "nb@qa.com", DATE = "2020-09-18 01:00:00";
		final Order created = new Order(ORDERID, CUSTID, FNAME, SNAME, EMAIL, DATE);

		Mockito.when(utils.getLong()).thenReturn(ORDERID, CUSTID);
		Mockito.when(utils.getString()).thenReturn(FNAME, SNAME, EMAIL, DATE);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(4)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> order = new ArrayList<>();
		order.add(new Order(3L, 1L, "jordan", "harrison", "jh@qa.com", "2020-09-18 01:00:00"));

		Mockito.when(dao.readAll()).thenReturn(order);

		assertEquals(order, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}