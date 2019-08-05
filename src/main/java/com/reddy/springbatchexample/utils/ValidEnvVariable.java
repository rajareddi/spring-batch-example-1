package com.reddy.springbatchexample.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Payload;
@Documented
@Target({ElementType.PARAMETER})
public @interface ValidEnvVariable {

    String[] acceptedValues();
    String message() default "ERROR";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
