package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;

public interface IAccountService {

	public boolean createAccount(CustomerBean customerBean) throws Exception;

	public boolean deposit(CustomerBean customerBean, double depositAmount)
			throws Exception;

	public boolean withdraw(CustomerBean customerBean, double withdrawAmount)
			throws Exception;

	public boolean fundTransfer(CustomerBean transferingCustomerBean,
			CustomerBean beneficiaryCustomerBean, double transferAmount)
			throws Exception;

	public CustomerBean findAccount(int accountId) throws Exception;

	public boolean validate(CustomerBean bean) throws CustomerException;

}
