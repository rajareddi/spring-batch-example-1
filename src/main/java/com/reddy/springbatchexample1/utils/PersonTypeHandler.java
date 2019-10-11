package com.reddy.springbatchexample1.utils;


import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

public class PersonTypeHandler implements TypeHandler
{
    
    public Object parse(String text) throws TypeConversionException
    {
        PersonType personType = PersonType.valueOf(text.trim());
        return personType;
    }
    
    public String format(Object value)
    {
        return value != null ? ((PersonType) value).name() : PersonType.SINGLE.name();
    }
    
    public Class<?> getType()
    {
        return PersonType.class;
    }
}
