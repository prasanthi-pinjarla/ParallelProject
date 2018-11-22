package com.capg.paymentwallet.ui;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {

	IAccountService service = new AccountServiceImpl();
	CustomerBean customer = new CustomerBean();
	Scanner scanner = new Scanner(System.in);
	AccountBean account = new AccountBean();

	public static void main(String[] args) throws Exception {
		char ch;
		Client client = new Client();
		do {
			System.out.println("\t******************************************");
			System.out.println("\t# \t Payment wallet application \t #");
			System.out.println("\t******************************************");
			System.out.println("\t 1. Create Account ");
			System.out.println("\t 2. Show Balance ");
			System.out.println("\t 3. Deposit ");
			System.out.println("\t 4. Withdraw ");
			System.out.println("\t 5. Fund Transfer");
			System.out.println("\t 6. Print Transactions");
			System.out.println("\t 7. Exit");
			System.out.print("Choose an option : ");
			int option = client.scanner.nextInt();

			switch (option) {
			case 1:
				client.create();

				break;
			case 2:
				client.showbalance();

				break;

			case 3:
				client.deposit();

				break;

			case 4:
				client.withdraw();

				break;

			case 5:
				client.fundtransfer();

				break;

			case 6:
				client.printTransaction();

				break;
			case 7:
				System.out.println("Exiting from the application...");
				System.exit(0);

				break;

			default:
				System.out.println("Invalid option");
				break;
			}

			System.out.print("\n\n***Do you want to continue press Y/N***: ");
			ch = client.scanner.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');

	}

	public void create() throws Exception {

		System.out.print("\t Enter Customer firstname\t\t: ");
		String fname = scanner.next();

		System.out.print("\t Enter Customer lastname\t\t: ");
		String lname = scanner.next();

		System.out.print("\t Enter  Customer  email id\t\t: ");
		String email = scanner.next();

		System.out.print("\t Enter  Customer  phone number\t\t: ");
		String phone = scanner.next();

		System.out.print("\t Enter  Customer PAN number\t\t: ");
		String pan = scanner.next();

		System.out.print("\t Enter  Customer  address\t\t: ");
		String address = scanner.next();

		CustomerBean customerBean = new CustomerBean();
		customerBean.setAddress(address);
		customerBean.setEmailId(email);
		customerBean.setPanNum(pan);
		customerBean.setPhoneNo(phone);
		customerBean.setFirstName(fname);
		customerBean.setLastName(lname);

		Date dateOfOpening = new Date();

		System.out.print("\t Enter balance to create account\t: ");
		double balance = scanner.nextDouble();

		AccountBean accountBean = new AccountBean();
		// accountBean.setAccountId(accId);
		accountBean.setBalance(balance);
		accountBean.setInitialDeposit(balance);
		accountBean.setDateOfOpening(dateOfOpening);
		//accountBean.setCustomerBean(customerBean);
		customerBean.setAccountBean(accountBean);
		boolean result = service.createAccount(customerBean);
		//boolean result = service.createAccount(accountBean);

		if (result) {
			System.out.println("\n****Congratulations, Customer account has been created successfully...****\n");
			System.out.println("Your account number: "+accountBean.getaccountNo());
		} else {
			System.out.println("\n\t***Enter valid details..***\n\t");
		}
	}

	public void showbalance() throws CustomerException, Exception {
		System.out.print("\t Enter Account ID\t\t: ");
		int accId = scanner.nextInt();
		 
		 CustomerBean customerBean = service.findAccount(accId);

		if (customerBean == null) {
			System.err.println("\n\t\t*** Account Does not exist...Enter valid account number... ***\n\t\t");
			return;
		}

		double balance = customerBean.getAccountBean().getBalance();
		System.out.println("\t Name of the customer\t\t: " + customerBean.getFirstName() + " "
				+ customerBean.getLastName());
		System.out.println("\t Phone number\t\t\t: " + customerBean.getPhoneNo());
		System.out.println("\t Your balance is\t\t: " + balance);

	}

	public void deposit() throws Exception {
		System.out.print("\t Enter Account ID\t\t\t: ");
		int accId = scanner.nextInt();
		CustomerBean customerBean = service.findAccount(accId);

		if (customerBean == null) {
			System.err.println("\n*** Account Does not exist...Enter valid account number... ***\n");
			return;
		} else {
			System.out.print("\t Enter amount that you want to deposit\t: ");
			double depositAmt = scanner.nextDouble();

			WalletTransaction wt = new WalletTransaction();
			wt.setTransactionType(1);
			wt.setTransactionDate(new Date());
			wt.setTransactionAmt(depositAmt);
			wt.setBeneficiaryCustomerBean(null);

			customerBean.getAccountBean().addTransaction(wt);

			boolean result = service.deposit(customerBean, depositAmt);

			if (result) {
				System.out.println("\n**$$** Deposited Money into Account **$$** ");
			} else {
				System.err.println("\n *** NOT Deposited Money into Account *** \n");

			}
		}
	}

	public void withdraw() throws Exception {
		System.out.print("\t Enter Account ID\t\t: ");
		int accId = scanner.nextInt();
		CustomerBean customerBean = service.findAccount(accId);
		if (customerBean == null) {
			System.err.println("\n *** Account Does not exist...Enter valid account number... ***\n");
			return;
		} else {
			System.out.print("\t Your current balance is\t\t: "+customerBean.getAccountBean().getBalance());
			System.out.print("\t Enter amount that you want to withdraw\t\t: ");
			double withdrawAmt = scanner.nextDouble();

			WalletTransaction wt = new WalletTransaction();
			wt.setTransactionType(2);
			wt.setTransactionDate(new Date());
			wt.setTransactionAmt(withdrawAmt);
			wt.setBeneficiaryCustomerBean(null);

			customerBean.getAccountBean().addTransaction(wt);

			boolean result = service.withdraw(customerBean, withdrawAmt);
			if (result) {
				System.out.print("\t Withdaw Money from Account done\t");
			} else {
				System.out.print("\t Withdaw Money from Account -Failed\t");
			}
		}
	}

	public void fundtransfer() throws Exception {
		System.out.print("\t Enter Account ID to Transfer Money From\t\t: ");
		int srcAccId = scanner.nextInt();

		CustomerBean customerBean1 = service.findAccount(srcAccId);
		if (customerBean1 == null) {
			System.err.print("\t Account Does not exist...Enter valid account number...\n");
			return;
		} else {
			System.out.print("\t Enter Account ID to Transfer Money to\t\t:");
			int targetAccId = scanner.nextInt();

			CustomerBean customerBean2 = service.findAccount(targetAccId);
			if (customerBean2 == null) {
				System.err.print("\t Account Does not exist...Enter valid account number...\n");
				return;
			} else {
				System.out.print("\t Enter amount that you want to transfer\t\t:");
				double transferAmt = scanner.nextDouble();

				WalletTransaction wt = new WalletTransaction();
				wt.setTransactionType(3);
				wt.setTransactionDate(new Date());
				wt.setTransactionAmt(transferAmt);
				wt.setBeneficiaryCustomerBean(customerBean2);

				customerBean1.getAccountBean().addTransaction(wt);
			
				
				WalletTransaction wt1 = new WalletTransaction();
				wt1.setTransactionType(1);
				wt1.setTransactionDate(new Date());
				wt1.setTransactionAmt(transferAmt);
				wt1.setBeneficiaryCustomerBean(customerBean2);

				customerBean2.getAccountBean().addTransaction(wt1);

				boolean result = service.fundTransfer(customerBean1, customerBean2, transferAmt);

				if (result) {
					System.out.print("\n\t Transfering Money from Account done");
				} else {
					System.err.print("\n\t Transfering Money from Account Failed ");
				}
			}
		}
	}

	public void printTransaction() throws Exception {
		System.out.print("\t Enter Account ID for printing Transaction Details\t\t: ");
		int accId = scanner.nextInt();

		CustomerBean customerBean = service.findAccount(accId);
		if (customerBean == null) {
			System.err.println("\n *** Account Does not exist...Enter valid account number... ***\n");
			return;
		} else {
			List<WalletTransaction> transactions = customerBean.getAccountBean().getAllTransactions();
		System.out.println("\t Customer Details [ " + customerBean.getFirstName() + " "
				+ customerBean.getLastName() + " , " + customerBean.getEmailId()
				+ " , " + customerBean.getPhoneNo() + " , "
				+ customerBean.getAddress() + " ]");

		System.out.println("------------------------------------------------------------------");
		System.out.println("Transaction_Type \t Transaction_Date \tTransaction_Amount");
		System.out.println("------------------------------------------------------------------");

		for (WalletTransaction wt : transactions) {

			String str = "";
			if (wt.getTransactionType() == 1) {
				str = str + "DEPOSIT\t";
			}
			if (wt.getTransactionType() == 2) {
				str = str + "WITHDRAW";
			}
			if (wt.getTransactionType() == 3) {
				str = str + "FUND TRANSFER";
			}

			str = str + "\t\t" + wt.getTransactionDate();

			str = str + "\t\t" + wt.getTransactionAmt();
			System.out.println(str);
		}

		System.out.println("------------------------------------------------------------------");
		}
	}
}
