package com.reddy.springbatchexample.utils;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChildATest {

	@Mock
	ParentA parentA;
//	@Mock AParentA aParentA;
	ChildA childA = new ChildA();

	@Test
	public void testNumber() {
		String expet = "10";
		when(childA.getNumber()).thenReturn("10");
		String value = childA.getNumber();
		Assert.assertEquals(expet, value);

	}

	@Test
	public void testGetList() {
		int expet = 2;
		when(parentA.getList()).thenReturn(Stream.of("Raja", "Reddy").collect(Collectors.toList()));
		List<String> value = childA.getList();
		Assert.assertEquals(expet, value.size());

	}

}
