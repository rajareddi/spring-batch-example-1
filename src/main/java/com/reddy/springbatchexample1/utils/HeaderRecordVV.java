package com.reddy.springbatchexample1.utils;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;

@Group
public class HeaderRecordVV {
    @Record(minOccurs = 1)
    private RequestHeaders requestHeaders;

	public RequestHeaders getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(RequestHeaders requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
    

  
}