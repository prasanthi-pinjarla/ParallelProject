package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class AccountDAOImpl implements IAccountDao {

	IAccountService accountService;

	EntityManager em;

	@Override
	public boolean createAccount(CustomerBean customerBean) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(customerBean);
		if (valid) {
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();
			em.persist(customerBean);
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deposit(CustomerBean customerBean, double depositAmount) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(customerBean);
		if (valid) {
			customerBean.getAccountBean().setBalance(customerBean.getAccountBean().getBalance() + depositAmount);
			boolean result = updateAccount(customerBean);
			if (result) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;

		}
	}

	@Override
	public boolean withdraw(CustomerBean customerBean, double withdrawAmount) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(customerBean);
		if (valid) {
			customerBean.getAccountBean().setBalance(customerBean.getAccountBean().getBalance() - withdrawAmount);
			boolean result = updateAccount(customerBean);
			if (result) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;

		}
	}

	@Override
	public boolean fundTransfer(CustomerBean transferingCustomerBean, CustomerBean beneficiaryCustomerBean,
			double transferAmount) throws Exception {
		transferingCustomerBean.getAccountBean().setBalance(transferingCustomerBean.getAccountBean().getBalance() - transferAmount);
		transferingCustomerBean.getAccountBean().setBalance(transferingCustomerBean.getAccountBean().getBalance() + transferAmount);

		boolean result1 = updateAccount(transferingCustomerBean);
		boolean result2 = updateAccount(beneficiaryCustomerBean);
		return result1 && result2;
	}

	@Override
	public CustomerBean findAccount(int accountId) throws Exception {
		try {
			CustomerBean customerBean2 = new CustomerBean();
			em = JPAManager.createEntityManager();
			customerBean2 = em.find(CustomerBean.class, customerBean2.getAccountBean().getaccountNo());
			JPAManager.closeResources(em);
			return customerBean2;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateAccount(CustomerBean customerBean) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(customerBean);
		if (valid) {
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.merge(customerBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} else {
			return false;
		}

	}

}
