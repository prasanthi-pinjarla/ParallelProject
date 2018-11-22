package com.capg.paymentwallet.exception;

public class CustomerExceptionMessage extends CustomerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String Error_in_firstName ="First Name should be more than 4 characters";
	public static final String Error_in_lastName ="Last Name should be more than 4 characters";
	public static final String Error_in_Email = "Email should be valid one";
	public static final String Error_in_PAN_Number = "Pan number should contain capital letters and digits only and lendth equals to 10";
	public static final String Error_in_PhoneNumber = "phone number should start with [6-9] and contain 10 digits";
	public static final String Error_in_balance = "Balance should be greater than or equal to 500";
	public static final String Error_in_Address = "Address should not be null";
	public static final String Error_in_Initial_Deposit ="Initial deposit should be greater than 500";

	
	

}
