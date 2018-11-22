package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{
	
	IAccountDao dao;
	@Override
	public boolean createAccount(CustomerBean customerBean)
			throws Exception {
		dao =new AccountDAOImpl();
		return dao.createAccount(customerBean);
	}

	

	@Override
	public boolean deposit(CustomerBean customerBean, double depositAmount)
			throws Exception {
		dao =new AccountDAOImpl();
		return dao.deposit(customerBean, depositAmount);
	}

	@Override
	public boolean withdraw(CustomerBean customerBean, double withdrawAmount)
			throws Exception {
		dao =new AccountDAOImpl();
		return dao.withdraw(customerBean, withdrawAmount);
	}

	@Override
	public boolean fundTransfer(CustomerBean transferingCustomerBean,
			CustomerBean beneficiaryCustomerBean, double transferAmount) throws Exception {
		dao =new AccountDAOImpl();
		return dao.fundTransfer(transferingCustomerBean, beneficiaryCustomerBean, transferAmount);
	}

	@Override
	public CustomerBean findAccount(int accountId) throws Exception {
		dao =new AccountDAOImpl();
		return dao.findAccount(accountId);
	}


	@Override
	public boolean validate(CustomerBean bean) throws CustomerException  {
        boolean isValid = false;
		/*int value = Double.compare(bean.getAccountBean().getInitialDeposit(),500);*/
        if (!(bean.getFirstName().matches("[a-zA-Z]{3,25}"))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_firstName);
        } 
        else if (!(bean.getLastName().matches("[a-zA-Z]{3,25}"))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_lastName);
        } 
        else if (!(bean.getEmailId().matches("[a-zA-Z][a-zA-z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+"))) {
        				throw new CustomerException(CustomerExceptionMessage.Error_in_Email);
        } 
        else if (!(String.valueOf(bean.getPhoneNo())
                                        .matches("(0)?[7-9]{1}[0-9]{9}"))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_PhoneNumber);
        } 
        else if ((bean.getPanNum() == null)
				|| (!(bean.getPanNum()
						.matches("^[A-Z]{1}[A-Z0-9]{9}")))) {
			            throw new CustomerException(CustomerExceptionMessage.Error_in_PAN_Number);
		}
        else if ((bean.getAddress() == null)
				|| (!(bean.getAddress()
						.matches("[A-Za-z0-9]{3,50}")))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_Address);
        } 
        /*else if (!balanceCheck(bean.getAccountBean().getBalance(), bean.getAccountBean().getMinimumBalance())) {

			throw new CustomerException(CustomerExceptionMessage.Error_in_balance);

		}*/
        else if (balanceCheck(bean.getAccountBean().getInitialDeposit(), bean.getAccountBean().getMinimumBalance())) {

			throw new CustomerException(CustomerExceptionMessage.Error_in_Initial_Deposit);

		}
        else {
                        isValid = true;
        }
        return isValid;
	}
		public boolean balanceCheck(double value1,double value2){
			boolean status = false;
			int value = Double.compare(value1,value2);
			if(!(value<=0)){
			return true;
			}
			else{
				return status;
			}
		}
}