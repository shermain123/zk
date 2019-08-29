package com.cn.member.service.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.Res;

public interface ResourceService {

	List<Res> searchAllResource(Map<String,Object> map);
	
	List<Res> searchResource(Map<String,Object> map);
	
	void inserRes(Res res);
	
	void updateRes(Res res);
	
	void deleteRes(String id);
	
	Integer getMaxId();
}
