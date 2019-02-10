package gokhan.blueharvest.transaction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import gokhan.blueharvest.transaction.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	@Transactional
	void deleteByAccountId(int accountId);

	List<Transaction> findByAccountId(int accountId);
}
