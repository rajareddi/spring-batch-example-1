package com.reddy.springbatchexample1.model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

import lombok.Getter;
import lombok.Setter;

///https://docs.spring.io/spring-batch/2.0.x/reference/html/readersAndWriters.html
@Record(name = "requestEntity")

public class RequestEntity {
	@Field(length = 15, maxLength = 15, minLength = 0, align = Align.LEFT)
	public String request_id;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String entity_id;

	@Field(length = 10, maxLength = 10, minLength = 0, align = Align.LEFT)
	public String depot_id;

	@Field(length = 10, maxLength = 10, minLength = 0, align = Align.LEFT)
	public String event_id;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String event_type;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String event_calssification;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String security_numbering_scheme;

	@Field(length = 12, maxLength = 12, minLength = 0, align = Align.LEFT)
	public String security_id;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String request_date;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String request_type;

	@Field(length = 36, maxLength = 36, minLength = 0, align = Align.LEFT)
	public String request_type_description;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String request_date_from;

	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	public String request_date_to;

	@Field(length = 14, maxLength = 14, minLength = 0, align = Align.LEFT)
	public String creation_date;

	@Field(length = 14, maxLength = 14, minLength = 0, align = Align.LEFT)
	public String previous_date_time;

	@Field(length = 16, maxLength = 16, minLength = 0, align = Align.LEFT)
	public String account_system;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(String depot_id) {
		this.depot_id = depot_id;
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

	public String getEvent_calssification() {
		return event_calssification;
	}

	public void setEvent_calssification(String event_calssification) {
		this.event_calssification = event_calssification;
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

	public String getRequest_date() {
		return request_date;
	}

	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getRequest_type_description() {
		return request_type_description;
	}

	public void setRequest_type_description(String request_type_description) {
		this.request_type_description = request_type_description;
	}

	public String getRequest_date_from() {
		return request_date_from;
	}

	public void setRequest_date_from(String request_date_from) {
		this.request_date_from = request_date_from;
	}

	public String getRequest_date_to() {
		return request_date_to;
	}

	public void setRequest_date_to(String request_date_to) {
		this.request_date_to = request_date_to;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public String getPrevious_date_time() {
		return previous_date_time;
	}

	public void setPrevious_date_time(String previous_date_time) {
		this.previous_date_time = previous_date_time;
	}

	public String getAccount_system() {
		return account_system;
	}

	public void setAccount_system(String account_system) {
		this.account_system = account_system;
	}

	@Override
	public String toString() {
		StringBuilder  builder= new StringBuilder();
		builder.append("account_system").append("=").append(account_system).append("\n")
		.append("request_date").append("=").append(request_date).append("\n");
		return super.toString();
	}

}
