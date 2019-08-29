package com.cn.member.mapper.custdata;

import java.util.List;

import com.cn.common.domain.cust.CustData;

public interface CustDataMapper {

	List<CustData> getCustList();
	
	List<CustData> getAttList(Integer id);
}
