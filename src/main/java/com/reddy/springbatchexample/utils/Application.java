package com.reddy.springbatchexample.utils;

import javax.validation.Validation;
import javax.validation.Validator;

import com.reddy.springbatchexample1.utils.UserNotBlank;

public class Application {

	public static void main(String[] args) throws Exception {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		UserNotBlank user = new UserNotBlank(" ");
		validator.validate(user).stream().forEach(violation -> System.out.println(violation.getMessage()));
	}
}
