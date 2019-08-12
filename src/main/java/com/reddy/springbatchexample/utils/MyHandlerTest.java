package com.reddy.springbatchexample.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class MyHandlerTest {

	private MyDependency mockDependency;
	private MyHandler realHandler;

	@Before
	public void setup() {
		mockDependency = Mockito.mock(MyDependency.class);
		realHandler = new MyHandler();
		// realhandler.setDependency(mockDependency); // but you might Springify
		// this
	}

	@Test
	public void testSomeMethod() {
		ReflectionTestUtils.setField(realHandler, "myDependency", mockDependency);
		Mockito.when(mockDependency.otherMethod()).thenReturn(10);
		// specify behaviour of mock
		when(mockDependency.otherMethod()).thenReturn(1);

		// really call the method under test
		realHandler.someMethod();
	}

}
