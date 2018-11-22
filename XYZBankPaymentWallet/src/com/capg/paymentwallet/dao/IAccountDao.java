package com.capg.paymentwallet.dao;

import com.capg.paymentwallet.bean.CustomerBean;

public interface IAccountDao {

	public boolean createAccount(CustomerBean customerBean) throws Exception;

	public boolean deposit(CustomerBean customerBean, double depositAmount)
			throws Exception;

	public boolean withdraw(CustomerBean customerBean, double withdrawAmount)
			throws Exception;

	public boolean fundTransfer(CustomerBean transferingCustomerBean,
			CustomerBean beneficiaryCustomerBean, double transferAmount)
			throws Exception;

	public CustomerBean findAccount(int accountId) throws Exception;

	public boolean updateAccount(CustomerBean customerBean) throws Exception;

}
