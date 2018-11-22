package com.capg.paymentwallet.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Account_Details1")
public class AccountBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountNo;
	
	private double balance;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfOpening;
	
	private double initialDeposit;
	
	private double minimumBalance = 500;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<WalletTransaction> allTransactions;

	public int getaccountNo() {
		return accountNo;
	}

	public void setaccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDateOfOpening() {
		return dateOfOpening;
	}

	public void setDateOfOpening(Date dateOfOpening) {
		this.dateOfOpening = dateOfOpening;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public List<WalletTransaction> getAllTransactions() {
		return allTransactions;
	}

	public void setAllTransactions(List<WalletTransaction> allTransactions) {
		this.allTransactions = allTransactions;
	}
	
	public double getMinimumBalance() {
		return minimumBalance;
	}

	
	
	@Override
	public String toString() {
		return "AccountBean [accountNo=" + accountNo + ", balance=" + balance
				+  ", dateOfOpening="
				+ dateOfOpening + ", initialDeposit=" + initialDeposit
				+ ", allTransactions=" + allTransactions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountBean other = (AccountBean) obj;
		if (accountNo != other.accountNo)
			return false;
		return true;
	}
	
	public void addTransaction(WalletTransaction wt){
		this.allTransactions.add(wt);
	}

	
	

}

