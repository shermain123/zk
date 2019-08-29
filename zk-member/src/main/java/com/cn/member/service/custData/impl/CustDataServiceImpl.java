package com.cn.member.service.custData.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.cust.CustData;
import com.cn.member.mapper.custdata.CustDataMapper;
import com.cn.member.service.custData.CustDataService;

@Service("custDataService")
public class CustDataServiceImpl implements CustDataService {

	@Resource(name = "custDataMapper")
	private CustDataMapper custDataMapper;
	
	@Override
	public List<CustData> getCustList() {
		
		return custDataMapper.getCustList();
	}

	@Override
	public List<CustData> getAttList(Integer id) {
		
		return custDataMapper.getAttList(id);
	}

}
