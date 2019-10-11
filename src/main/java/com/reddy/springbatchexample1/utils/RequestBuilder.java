package com.reddy.springbatchexample1.utils;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import com.reddy.springbatchexample1.model.RequestEntity;

@Record(name="requestBuilder")
public class RequestBuilder {
	
	@Record
	RequestHeaders requestHeaders;
	
	@Record
	List<RequestEntity> requestEntity;

	public RequestHeaders getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(RequestHeaders requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public List<RequestEntity> getRequestEntity() {
		return requestEntity;
	}

	public void setRequestEntity(List<RequestEntity> requestEntity) {
		this.requestEntity = requestEntity;
	}

}
