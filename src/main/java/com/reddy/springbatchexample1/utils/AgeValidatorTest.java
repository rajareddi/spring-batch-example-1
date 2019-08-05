package com.reddy.springbatchexample1.utils;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(LocalDate.class)
@RunWith(MockitoJUnitRunner.class)
public class AgeValidatorTest {
	Validator vlidator;
	@Mock
	LocalDate date;
	@Mock
	ConstraintValidatorContext constraintValidatorContext;
	AgeValidator ageValidator;

	;
	private @Age(value = 18) Age ageValue = new Age() {

		@Override
		public Class<? extends Annotation> annotationType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String message() {
			// TODO Auto-generated method stub
			return "Test case message";
		}

		@Override
		public Class<?>[] groups() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Class<? extends Payload>[] payload() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long value() {
			// TODO Auto-generated method stub
			return 20;
		}

	};

	@Before
	public void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		vlidator = validatorFactory.getValidator();
		ageValidator = new AgeValidator();

	}

	@Test
	public void testIsValid() {
		ageValidator.initialize(ageValue);
		ageValidator.isValid(date, constraintValidatorContext);

	}

}
