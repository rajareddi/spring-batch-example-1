package com.reddy.springbatchexample1.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class SummeryReport {
	private String Trade_Refernce;
	private String Activity_Type;
	private String Effective_Date;
	private String Original_Settlement_Date;
	private String Loan_Quantity;
	private String Counterparty_Code;
	private String Fund_Code;
	private String Input_Date;
	private String Input_Time;
	private String ISIN_Code;
	private String Cusip_Code;
	private String Sedol_Code;
	private String Initial_Loan_Quantity;
	private String Prior_Qunatity;
	private String Fund_Delivery;
	private String Account_Number;

}
