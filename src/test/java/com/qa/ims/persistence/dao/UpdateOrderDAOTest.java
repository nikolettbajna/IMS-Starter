package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class UpdateOrderDAOTest {

	private final UpdateOrderDAO UpdateDAO = new UpdateOrderDAO();
	private final java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	private final Date date = new Date(); 

	@Before
	public void setup() {
		DBUtils.connect("src/test/resources/db.properties");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testAdd() {
		final OrderItem added = new OrderItem(5L, 2L, 2L, "monopoly", "boardgame", 29.55);
		assertEquals(added, UpdateDAO.add(added));
	}

	@Test
	public void testView() {
		List<OrderItem> expected = new ArrayList<>();
		expected.add(new OrderItem(2L, 1L));
		expected.add(new OrderItem(1L, 1L));
		expected.add(new OrderItem(1L, 1L));
		assertEquals(expected, UpdateDAO.view(1));
	}

	@Test
	public void testReadLatest() {
		assertEquals(new OrderItem(3L, 2L), UpdateDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new OrderItem(1L, 1L), UpdateDAO.readOrder(ID));
	}

	@Test
	public void testRemove() {
		assertEquals(1, 1, UpdateDAO.remove(1, 1));
	}
}
