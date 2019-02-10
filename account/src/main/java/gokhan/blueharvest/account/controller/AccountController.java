package gokhan.blueharvest.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gokhan.blueharvest.account.constant.ErrorMessage;
import gokhan.blueharvest.account.exception.NotFoundException;
import gokhan.blueharvest.account.exception.ServiceDownException;
import gokhan.blueharvest.account.model.Account;
import gokhan.blueharvest.account.model.Customer;
import gokhan.blueharvest.account.model.Transaction;
import gokhan.blueharvest.account.service.AccountService;
import gokhan.blueharvest.account.service.CustomerService;

/**
 * 
 * @author gcelikkaya
 *
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;
	private CustomerService customerService;

	@Autowired
	public AccountController(AccountService accountService, CustomerService customerService) {
		this.accountService = accountService;
		this.customerService = customerService;
	}

	/**
	 * Returns transaction history of the account
	 * 
	 * @param accountId
	 *            id of the account
	 * @return transaction history of the account
	 */
	@GetMapping("/{accountId}/transactions")
	public List<Transaction> getAccountTransactions(@PathVariable int accountId) {
		return accountService.getAccountTransactions(accountId);
	}

	/**
	 * Inserts a new input to the account
	 *
	 * @param input
	 *            transaction input object: contains accountId and transaction amount
	 * @return inserted input object
	 */
	@PostMapping("/transactions")
	public Transaction addNewTransaction(@RequestBody Transaction input) throws NotFoundException, ServiceDownException {
		Transaction transaction = accountService.addTransaction(input.getAccountId(), input.getTransactionAmount());
		if (transaction == null) {
			throw new ServiceDownException(ErrorMessage.SERVICE_NOT_AVAILABLE);
		}
		return transaction;
	}

	/**
	 * Creates a new input for the customer
	 *
	 * @param input
	 *            account object: contains customerId and initialBalance
	 * @return created input object
	 * @throws NotFoundException
	 *             if the given customer does not exist
	 */
	@PostMapping
	public Account createAccount(@RequestBody @Valid Account input) throws NotFoundException, ServiceDownException {
		Customer customer = customerService.findCustomerById(input.getCustomerId());
		if (customer == null) {
			throw new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
		}

		Account account = accountService.createAccount(input);
		if (account == null) {
			throw new ServiceDownException(ErrorMessage.SERVICE_NOT_AVAILABLE);
		}
		return account;
	}
}
