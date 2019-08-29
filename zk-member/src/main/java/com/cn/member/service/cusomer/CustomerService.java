package com.cn.member.service.cusomer;

import java.util.List;

import com.cn.common.domain.cust.Customer;

public interface CustomerService {

	Customer queryCusid(String cusid);
	
	List<Customer> queryAll();
	
	int insert(Customer customer);
	
	int update(Customer customer);
	
	int delByCusid(String cusid);
	
}
