package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class AccountDAOImpl implements IAccountDao {

	IAccountService accountService;

	EntityManager em;

	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(accountBean);
		if (valid) {
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();
			em.persist(accountBean);
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(accountBean);
		if (valid) {
			accountBean.setBalance(accountBean.getBalance() + depositAmount);
			boolean result = updateAccount(accountBean);
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
	public boolean withdraw(AccountBean accountBean, double withdrawAmount) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(accountBean);
		if (valid) {
			accountBean.setBalance(accountBean.getBalance() - withdrawAmount);
			boolean result = updateAccount(accountBean);
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
	public boolean fundTransfer(AccountBean transferingAccountBean, AccountBean beneficiaryAccountBean,
			double transferAmount) throws Exception {
		transferingAccountBean.setBalance(transferingAccountBean.getBalance() - transferAmount);
		beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance() + transferAmount);

		boolean result1 = updateAccount(transferingAccountBean);
		boolean result2 = updateAccount(beneficiaryAccountBean);
		return result1 && result2;
	}

	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		try {
			em = JPAManager.createEntityManager();
			AccountBean accountBean2 = em.find(AccountBean.class, accountId);
			JPAManager.closeResources(em);
			return accountBean2;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		accountService = new AccountServiceImpl();
		boolean valid = false;
		valid = accountService.validate(accountBean);
		if (valid) {
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.merge(accountBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} else {
			return false;
		}

	}

}
