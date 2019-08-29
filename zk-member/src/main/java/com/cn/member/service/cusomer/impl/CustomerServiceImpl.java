package com.cn.member.service.cusomer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.cust.Customer;
import com.cn.member.mapper.customer.CustomerMapper;
import com.cn.member.service.cusomer.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "customerMapper")
	private CustomerMapper customerMapper;
	
	@Override
	public Customer queryCusid(String cusid) {
		
		return customerMapper.queryCusid(cusid);
	}

	@Override
	public List<Customer> queryAll() {
		
		return customerMapper.queryAll();
	}

	@Override
	public int insert(Customer customer) {
		
		return customerMapper.insert(customer);
	}

	@Override
	public int update(Customer customer) {
		
		return customerMapper.update(customer);
	}

	@Override
	public int delByCusid(String cusid) {
		
		return customerMapper.delByCusid(cusid);
	}

}
