package com.cn.member.service.system;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.Res;

public interface ResCacheService {

	void initRes();

	Res getResByKey(String key);

	void addRes(Res res);
	
	void updateRes(Res res);
	
	void deleteRes(Res res);
}
