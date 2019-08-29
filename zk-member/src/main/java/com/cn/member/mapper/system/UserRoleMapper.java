package com.cn.member.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.UserRole;
import com.cn.member.mapper.BaseMapper;


public interface UserRoleMapper extends BaseMapper<UserRole,String> {

	List<UserRole> searchUserRole(Map<String, Object> map);
	
	int deleteUserRoleByMap(Map<String, Object> map);
}
