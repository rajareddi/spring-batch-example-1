package com.reddy.springbatchexample1.model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@Record(minOccurs = 1)
public class ResponseEntity {

	@Field(at = 1, maxLength = 8)
	private String entity_id;

	@Field(at = 2, maxLength = 10)
	private String event_id;

	@Field(at = 3, maxLength = 8)
	private String event_type;

	@Field(at = 4, maxLength = 10)
	private String depot_id;

	@Field(at = 5, maxLength = 8)
	private String security_numbering_scheme;

	@Field(at = 6, maxLength = 12)
	private String security_id;

	@Field(at = 7, maxLength = 12)
	private String security_number;

	@Field(at = 8, maxLength = 12)
	private String account_id;

	@Field(at = 9, maxLength = 36)
	private String location_id;

	@Field(at = 10, maxLength = 8)
	private String receipt_delivery_indicator;

	@Field(at = 11, maxLength = 16)
	private String transction_id;

	@Field(at = 12, maxLength = 16)
	private String linked_transction_id;

	@Field(at = 13, maxLength = 8)
	private String transction_date;

	@Field(at = 14, maxLength = 16)
	private String contractual_transaction_date;

	@Field(at = 15, maxLength = 8)
	private String actuval_settlement_date;

	@Field(at = 16, maxLength = 8)
	private String deposit_date;

	@Field(at = 17, maxLength = 8)
	private String withdrawal_date;

	@Field(at = 18, maxLength = 47)
	private double traded_quantity;

	@Field(at = 19, maxLength = 47)
	private String settled_quantity;

	@Field(at = 20, maxLength = 8)
	private String transaction_type;

	@Field(at = 21, maxLength = 8)
	private String transaction_catalgory;

	@Field(at = 22, maxLength = 16)
	private String transaction_client_reference;

	@Field(at = 23, maxLength = 36)
	private String counter_party_id;

	@Field(at = 24, maxLength = 36)
	private String broker_id;

	@Field(at = 25, maxLength = 36)
	private String accounting_system;

	@Field(at = 26, maxLength = 8)
	private String status;

	@Field(at = 27, maxLength = 14)
	private String response_date_time;

	@Field(at = 28, maxLength = 15)
	private String request_id;

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(String depot_id) {
		this.depot_id = depot_id;
	}

	public String getSecurity_numbering_scheme() {
		return security_numbering_scheme;
	}

	public void setSecurity_numbering_scheme(String security_numbering_scheme) {
		this.security_numbering_scheme = security_numbering_scheme;
	}

	public String getSecurity_id() {
		return security_id;
	}

	public void setSecurity_id(String security_id) {
		this.security_id = security_id;
	}

	public String getSecurity_number() {
		return security_number;
	}

	public void setSecurity_number(String security_number) {
		this.security_number = security_number;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getReceipt_delivery_indicator() {
		return receipt_delivery_indicator;
	}

	public void setReceipt_delivery_indicator(String receipt_delivery_indicator) {
		this.receipt_delivery_indicator = receipt_delivery_indicator;
	}

	public String getTransction_id() {
		return transction_id;
	}

	public void setTransction_id(String transction_id) {
		this.transction_id = transction_id;
	}

	public String getLinked_transction_id() {
		return linked_transction_id;
	}

	public void setLinked_transction_id(String linked_transction_id) {
		this.linked_transction_id = linked_transction_id;
	}

	public String getTransction_date() {
		return transction_date;
	}

	public void setTransction_date(String transction_date) {
		this.transction_date = transction_date;
	}

	public String getContractual_transaction_date() {
		return contractual_transaction_date;
	}

	public void setContractual_transaction_date(String contractual_transaction_date) {
		this.contractual_transaction_date = contractual_transaction_date;
	}

	public String getActuval_settlement_date() {
		return actuval_settlement_date;
	}

	public void setActuval_settlement_date(String actuval_settlement_date) {
		this.actuval_settlement_date = actuval_settlement_date;
	}

	public String getDeposit_date() {
		return deposit_date;
	}

	public void setDeposit_date(String deposit_date) {
		this.deposit_date = deposit_date;
	}

	public String getWithdrawal_date() {
		return withdrawal_date;
	}

	public void setWithdrawal_date(String withdrawal_date) {
		this.withdrawal_date = withdrawal_date;
	}

	public double getTraded_quantity() {
		return traded_quantity;
	}

	public void setTraded_quantity(double traded_quantity) {
		this.traded_quantity = traded_quantity;
	}

	public String getSettled_quantity() {
		return settled_quantity;
	}

	public void setSettled_quantity(String settled_quantity) {
		this.settled_quantity = settled_quantity;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getTransaction_catalgory() {
		return transaction_catalgory;
	}

	public void setTransaction_catalgory(String transaction_catalgory) {
		this.transaction_catalgory = transaction_catalgory;
	}

	public String getTransaction_client_reference() {
		return transaction_client_reference;
	}

	public void setTransaction_client_reference(String transaction_client_reference) {
		this.transaction_client_reference = transaction_client_reference;
	}

	public String getCounter_party_id() {
		return counter_party_id;
	}

	public void setCounter_party_id(String counter_party_id) {
		this.counter_party_id = counter_party_id;
	}

	public String getBroker_id() {
		return broker_id;
	}

	public void setBroker_id(String broker_id) {
		this.broker_id = broker_id;
	}

	public String getAccounting_system() {
		return accounting_system;
	}

	public void setAccounting_system(String accounting_system) {
		this.accounting_system = accounting_system;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponse_date_time() {
		return response_date_time;
	}

	public void setResponse_date_time(String response_date_time) {
		this.response_date_time = response_date_time;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

}
