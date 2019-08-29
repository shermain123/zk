package com.cn.member.service.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.RoleRes;

public interface RoleResService {

	void insertRoleRes(RoleRes roleRes);
	
	void insertBatch(List<RoleRes> list);

	int deleteRoleResByMap(Map<String, Object> map);
	
	List<RoleRes> searchRoleRes(Map<String,Object> map);

}
