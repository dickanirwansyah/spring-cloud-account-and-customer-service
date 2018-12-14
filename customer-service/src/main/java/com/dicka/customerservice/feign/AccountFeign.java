package com.dicka.customerservice.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dicka.customerservice.model.Account;

@FeignClient("account-service")
public interface AccountFeign {

	@RequestMapping(value = "/accounts/customer/{customerId}", method = RequestMethod.GET)
	List<Account> getAccounts(@PathVariable("customerId") Integer customerId);
	
}
