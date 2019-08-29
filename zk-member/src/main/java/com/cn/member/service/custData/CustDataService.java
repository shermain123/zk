package com.cn.member.service.custData;

import java.util.List;

import com.cn.common.domain.cust.CustData;

public interface CustDataService {

	List<CustData> getCustList();
	
	List<CustData> getAttList(Integer id);
}
