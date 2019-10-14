package com.reddy.springbatchexample1.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RequestEntityTest {
	RequestEntity requestEntity;

	@Before
	public void setUp() {
		requestEntity = new RequestEntity();
		requestEntity.setRequest_id("123");
		requestEntity.setEntity_id("GVGCA");
		requestEntity.setDepot_id("538841754408473");
		requestEntity.setEvent_id("4567");
	}

	@Test
	public void testGetRequest_id() {
		assertEquals("123", requestEntity.getRequest_id());
	}

	@Test
	public void testGetEntity_id() {
		assertEquals("GVGCA", requestEntity.getEntity_id());

	}

	@Test
	public void testGetDepot_id() {

		assertEquals("538841754408473", requestEntity.getDepot_id());
	}

	@Test
	public void testGetEvent_id() {
		assertEquals("4567", requestEntity.getEvent_id());
	}

	@Test
	public void testGetEvent_type() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEvent_calssification() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSecurity_numbering_scheme() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSecurity_id() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequest_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequest_type() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequest_type_description() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequest_date_from() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequest_date_to() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreation_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrevious_date_time() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccount_system() {
		fail("Not yet implemented");
	}

}
