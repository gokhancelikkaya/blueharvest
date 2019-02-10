package gokhan.blueharvest.account.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private int id;
	private int customerId;

	@PositiveOrZero
	private long initialBalance;

	private long balance;

	public Account() {
	}

	public Account(int customerId, long initialBalance) {
		this.customerId = customerId;
		this.initialBalance = initialBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public long getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(long initialBalance) {
		this.initialBalance = initialBalance;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public void addBalance(long transactionAmount) {
		balance += transactionAmount;
	}
}
