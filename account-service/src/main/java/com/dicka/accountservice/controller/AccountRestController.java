package com.dicka.accountservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.accountservice.model.Account;

@RestController
@RequestMapping(value = "/accounts")
public class AccountRestController {

	private List<Account> accountList;
	protected Logger logger = Logger.getLogger(AccountRestController.class.getName());
	
	public AccountRestController(){
		/** DUMMY DATA ACCOUNT **/
		accountList = new ArrayList<Account>();
		accountList.add(new Account(1, 1, "111"));
		accountList.add(new Account(2, 2, "222"));
		accountList.add(new Account(3, 3, "333"));
		accountList.add(new Account(4, 4, "444"));
		accountList.add(new Account(5, 4, "555"));
		accountList.add(new Account(6, 6, "666"));
		accountList.add(new Account(7, 7, "777"));
	}
	
	@GetMapping("/customer/{customerId}")
	public List<Account> findByCustomerId(@PathVariable("customerId") Integer customerId){
		logger.info(String.format("Account.findByCustomerId", customerId));
		return accountList.stream()
				.filter(data -> data.getCustomerId()
						.intValue() == customerId.intValue())
							.collect(Collectors.toList());
	}
	
	@GetMapping("/{number}")
	public Account findByNumber(@PathVariable("number") String number){
		logger.info(String.format("Account.findByNumber(%s)", number));
		return accountList.stream()
				.filter(dataAccount -> dataAccount.getNumber()
						.equals(number))
						.findFirst()
						.get();
	}
	
	@GetMapping
	public List<Account> listAccount(){
		logger.info("Account.findAll()");
		return accountList;
	}
}
