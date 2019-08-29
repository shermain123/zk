package com.cn.member.mapper.customer;

import java.util.List;

import com.cn.common.domain.cust.Customer;

public interface CustomerMapper {

	Customer queryCusid(String cusid);
	
	List<Customer> queryAll();
	
	int insert(Customer customer);
	
	int update(Customer customer);
	
	int delByCusid(String cusid);
	
}
