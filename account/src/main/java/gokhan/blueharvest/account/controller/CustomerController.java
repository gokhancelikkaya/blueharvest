package gokhan.blueharvest.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gokhan.blueharvest.account.model.Account;
import gokhan.blueharvest.account.model.Customer;
import gokhan.blueharvest.account.service.AccountService;
import gokhan.blueharvest.account.service.CustomerService;

/**
 * 
 * @author gcelikkaya
 *
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private AccountService accountService;
	private CustomerService customerService;

	@Autowired
	public CustomerController(AccountService accountService, CustomerService customerService) {
		this.accountService = accountService;
		this.customerService = customerService;
	}

	/**
	 * Lists all existing customers
	 * 
	 * @return all existing customers
	 */
	@GetMapping
	private List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	/**
	 * Returns a specific customer
	 * 
	 * @param id
	 *            of the customer
	 * @return requested customer
	 */
	@GetMapping("/{id}")
	private Customer getCustomer(@PathVariable int id) {
		return customerService.findCustomerById(id);
	}

	/**
	 * Displays accounts of given customer
	 * 
	 * @param id
	 *            of the customer
	 * @return accounts of given customer
	 */
	@GetMapping("/{id}/accounts")
	private List<Account> getAccounts(@PathVariable int id) {
		return accountService.findAccountsByCustomerId(id);
	}

	/**
	 * Creates a new customer
	 * 
	 * @param customer
	 *            model object
	 * @return customer object after creation
	 */
	@PostMapping
	private Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}
}
