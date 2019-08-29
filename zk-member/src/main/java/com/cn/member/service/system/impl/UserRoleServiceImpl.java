package com.cn.member.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.UserRole;
import com.cn.member.mapper.system.UserRoleMapper;
import com.cn.member.service.system.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Resource(name = "userRoleMapper")
	private UserRoleMapper userRoleMapper;
	
	@Override
	public void insertUserRole(UserRole userRole) {
		userRoleMapper.insert(userRole);

	}

	@Override
	public int deleteUserRoleByMap(Map<String, Object> map) {
		return userRoleMapper.deleteUserRoleByMap(map);
	}

	@Override
	public List<UserRole> searchUserRole(Map<String, Object> map) {
		
		return userRoleMapper.searchUserRole(map);
	}

}
