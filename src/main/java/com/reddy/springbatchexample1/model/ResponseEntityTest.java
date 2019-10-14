package com.reddy.springbatchexample1.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseEntityTest {
	ResponseEntity responseEntity;

	@Before
	public void setUp() {
		responseEntity = new ResponseEntity();
		responseEntity.setEntity_id("1234");
		responseEntity.setEvent_id("3456");
	}

	@Test
	public void testGetEntity_id() {
		assertEquals("1234", responseEntity.getEntity_id());
	}

	@Test
	public void testGetEvent_id() {
		assertEquals("3456", responseEntity.getEvent_id());
	
	}

	@Test
	public void testGetEvent_type() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDepot_id() {
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
	public void testGetSecurity_number() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccount_id() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLocation_id() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReceipt_delivery_indicator() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransction_id() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLinked_transction_id() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransction_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetContractual_transaction_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActuval_settlement_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDeposit_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWithdrawal_date() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTraded_quantity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSettled_quantity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransaction_type() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransaction_catalgory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransaction_client_reference() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCounter_party_id() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccounting_system() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResponse_date_time() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequest_id() {
		fail("Not yet implemented");
	}

}
