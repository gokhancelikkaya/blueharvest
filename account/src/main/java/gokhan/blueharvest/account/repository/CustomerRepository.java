package gokhan.blueharvest.account.repository;

import org.springframework.data.repository.CrudRepository;

import gokhan.blueharvest.account.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
