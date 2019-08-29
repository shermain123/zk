package com.cn.member.service.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.UserRole;


public interface UserRoleService {

	void insertUserRole(UserRole userRole);

	int deleteUserRoleByMap(Map<String, Object> map);
	
	List<UserRole> searchUserRole(Map<String, Object> map);
}
