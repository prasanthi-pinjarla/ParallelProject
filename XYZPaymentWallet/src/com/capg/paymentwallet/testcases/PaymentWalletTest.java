package com.capg.paymentwallet.testcases;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class PaymentWalletTest {
	static IAccountService service = null;
	@BeforeClass
	public static void createInstance() 
	{
		service = new AccountServiceImpl();
	}

	@Test
	public  void testforPositiveCase() throws Exception //Positive Test case
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pinjarla");
	    customer.setAddress("Chennai");
	    customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123U");
		AccountBean bean = new AccountBean();
		
		bean.setBalance(1000);
		bean.setInitialDeposit(1000);
		bean.setDateOfOpening(new Date());
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		
		assertTrue(result);
	}
	
	/*
	 * Test cases for Last name
	 */
	
	@Test (expected = CustomerException.class)
	public  void testFirstNameNull() throws Exception //Testing first name for null 
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("");
		customer.setLastName("Pinjarla");
		 customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testFirstNameForOnlyChar() throws Exception //Testing first name for only characters 
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Tom@#");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123ME");
		
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testFirstNameForlength() throws Exception //Testing first name for length
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Tom");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123ME");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	/*
	 * Test cases for Last name
	 */

	@Test (expected =  CustomerException.class)
	public void testLastNameNull() throws Exception //Testing Last name for null 
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123ME");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testLastNameForOnlyChar() throws Exception //Testing last name for only characters
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pin#$@12la");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123ME");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testLastNameForlength() throws Exception //Testing last name for length
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pi");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123ME");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	/*
	 * Test cases for phone number
	 */
	
	@Test (expected = CustomerException.class)
	public void testPhoneNumberForlength() throws Exception //Testing phone number for 10 digits 
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER123ME");
		
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	/*
	 * Test cases for email id 
	 */
	
	@Test (expected = CustomerException.class)
	public void testEmailIdForNull() throws Exception // Testing EmailId for null
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("");
		customer.setPanNum("DIFFER123ME");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	/*
	 * Test cases for permanent account number (pan) 
	 */
	
	@Test (expected = CustomerException.class)
	public void testPanForSpecialCharacters() throws Exception //Testing Pan for special characters
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("BVDA%&1ERF");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test(expected =CustomerException.class)
	public void testPanForNull() throws Exception //Testing Pan for null
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	
	
	@Test (expected = CustomerException.class)
	public void testPanForLength() throws Exception     //Testing Pan for length
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("Prasanthi");
		customer.setLastName("Pinjarla");
		customer.setPhoneNo("9440380136");
		customer.setEmailId("prasanthi@gmail.com");
		customer.setPanNum("DIFFER");
		AccountBean bean = new AccountBean();
		bean.setaccountNo(101);
		bean.setBalance(1000.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	

	
}
