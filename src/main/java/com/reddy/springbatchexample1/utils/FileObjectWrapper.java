package com.reddy.springbatchexample1.utils;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Group;
import org.beanio.annotation.Record;

import com.reddy.springbatchexample1.model.RequestEntity;

@Group(minOccurs = 1, maxOccurs = 1, name = "fileobjectMaper")
public class FileObjectWrapper {

	@Record(minOccurs = 1, maxOccurs = 1)
	RequestHeaders requestHeaders;

	@Record(collection = List.class, minOccurs = 1)
	List<RequestEntity> requestEntity;

	public List<RequestEntity> getRequestEntity() {
		return requestEntity;
	}

	public void setRequestEntity(List<RequestEntity> requestEntity) {
		this.requestEntity = requestEntity;
	}

	public RequestHeaders getHeaderRecords() {
		return requestHeaders;
	}

	public void setHeaderRecords(RequestHeaders requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	@Override
	public String toString() {
		return String.valueOf(getRequestEntity());
	}

}
