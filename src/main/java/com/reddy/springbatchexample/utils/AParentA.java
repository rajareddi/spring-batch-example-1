package com.reddy.springbatchexample.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class AParentA<T> {

	List<T> getList() {
		return new ArrayList<T>();
	}

}
