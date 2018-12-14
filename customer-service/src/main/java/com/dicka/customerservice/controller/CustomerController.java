package com.dicka.customerservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.customerservice.feign.AccountFeign;
import com.dicka.customerservice.model.Account;
import com.dicka.customerservice.model.Customer;
import com.dicka.customerservice.model.CustomerType;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	protected Logger logger = Logger.getLogger(CustomerController.class.getName());
	private final AccountFeign accountFeign;
	private List<Customer> customers;
	
	@Autowired
	public CustomerController(AccountFeign accountFeign){
		this.accountFeign = accountFeign;
		customers = new ArrayList<Customer>();
		customers.add(new Customer(1, "12345", "dicka nirwansyah", CustomerType.INDIVIDUAL));
		customers.add(new Customer(2, "12346", "denada rosa florina", CustomerType.INDIVIDUAL));
		customers.add(new Customer(3, "12347", "ahmad luthfi yahya", CustomerType.INDIVIDUAL));
		customers.add(new Customer(4, "12348", "eva luthfia", CustomerType.INDIVIDUAL));
	}
	
	/** find by pesel **/
	@GetMapping(value = "/pesel/{pesel}")
	public Customer findByPesel(@PathVariable("pesel") String pesel){
		logger.info("--Customer.findByPesel()--");
		return customers.stream()
				.filter(c -> c.getPesel().equals(pesel))
				.findFirst().get();
	}
	
	@GetMapping(value = "/{id}")
	public Customer findById(@PathVariable("id") Integer id){
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> 
		it.getId().intValue()==id.intValue()).findFirst().get();
		List<Account> accounts = accountFeign.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}
	
	@GetMapping
	public List<Customer> findAll(){
		logger.info("--Customer.findAll()");
		return customers;
	}
}
