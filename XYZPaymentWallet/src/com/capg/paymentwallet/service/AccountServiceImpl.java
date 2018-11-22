package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{
	
	IAccountDao dao;
	@Override
	public boolean createAccount(AccountBean accountBean)
			throws Exception {
		dao =new AccountDAOImpl();
		return dao.createAccount(accountBean);
	}

	

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		dao =new AccountDAOImpl();
		return dao.deposit(accountBean, depositAmount);
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		dao =new AccountDAOImpl();
		return dao.withdraw(accountBean, withdrawAmount);
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount) throws Exception {
		dao =new AccountDAOImpl();
		return dao.fundTransfer(transferingAccountBean, beneficiaryAccountBean, transferAmount);
	}

	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		dao =new AccountDAOImpl();
		return dao.findAccount(accountId);
	}


	@Override
	public boolean validate(AccountBean bean) throws CustomerException  {
        boolean isValid = false;
        if (!(bean.getCustomerBean().getFirstName().matches("[a-zA-Z]{3,25}"))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_firstName);
        } 
        else if (!(bean.getCustomerBean().getLastName().matches("[a-zA-Z]{3,25}"))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_lastName);
        } 
        else if (!(bean.getCustomerBean().getEmailId().matches("[a-zA-Z][a-zA-z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+"))) {
        				throw new CustomerException(CustomerExceptionMessage.Error_in_Email);
        } 
        else if (!(String.valueOf(bean.getCustomerBean().getPhoneNo())
                                        .matches("(0)?[7-9]{1}[0-9]{9}"))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_PhoneNumber);
        } 
        else if ((bean.getCustomerBean().getPanNum() == null)
				|| (!(bean.getCustomerBean().getPanNum()
						.matches("^[A-Z]{1}[A-Z0-9]{9}")))) {
			            throw new CustomerException(CustomerExceptionMessage.Error_in_PAN_Number);
		}
        else if ((bean.getCustomerBean().getAddress() == null)
				|| (!(bean.getCustomerBean().getAddress()
						.matches("[A-Za-z0-9]{3,50}")))) {
                        throw new CustomerException(CustomerExceptionMessage.Error_in_Address);
        } 
       /* else if (bean.getBalance() == 0 || !(bean.getBalance() >500)) {

			throw new CustomerException(CustomerExceptionMessage.Error_in_balance);

		}*/
        else if (!balanceCheck(bean.getInitialDeposit(), bean.getMinimumBalance())) {

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
		if(value>=0){
		return true;
		}
		else{
			return status;
		}
	}

}
