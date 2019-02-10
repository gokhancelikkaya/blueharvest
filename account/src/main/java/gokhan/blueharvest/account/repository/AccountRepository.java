package gokhan.blueharvest.account.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gokhan.blueharvest.account.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	List<Account> findByCustomerId(int customerId);
}
