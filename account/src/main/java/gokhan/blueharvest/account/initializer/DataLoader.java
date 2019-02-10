package gokhan.blueharvest.account.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gokhan.blueharvest.account.exception.NotFoundException;
import gokhan.blueharvest.account.model.Account;
import gokhan.blueharvest.account.model.Customer;
import gokhan.blueharvest.account.service.AccountService;
import gokhan.blueharvest.account.service.CustomerService;

@Component
@Profile("default")
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	/**
	 * Insert some initial dummy data
	 */
	public void run(ApplicationArguments args) throws NotFoundException {
		accountService.deleteAllTransactions();
		Customer customer = customerService.save(new Customer("Gokhan", "Celikkaya"));
		Account account = accountService.createAccount(new Account(customer.getId(), 1000L));
		if (account != null) {
			accountService.addTransaction(account.getId(), -400);
		}
	}
}
