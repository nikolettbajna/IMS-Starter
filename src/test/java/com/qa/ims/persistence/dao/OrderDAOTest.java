package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();
	private final java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	private final Date date = new Date(); 

	@Before
	public void setup() {
		DBUtils.connect("src/test/resources/db.properties");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(4L, 2L, "nikolett", "bajna", "nb@qa.com", sdf.format(date));
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(3L, 1L, "jordan", "harrison", "jh@qa.com", sdf.format(date)));
		expected.add(new Order(2L, 3L, "john", "smith", "js@gmail.com", sdf.format(date)));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(3L, 1L, "jordan", "harrison", "jh@qa.com", sdf.format(date)), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(3L, 2L, "john", "smith", "js@gmail.com", sdf.format(date)), DAO.readOrder(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(3L, 2L, "john", "doe", "jd@gmail.com", sdf.format(date));
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
