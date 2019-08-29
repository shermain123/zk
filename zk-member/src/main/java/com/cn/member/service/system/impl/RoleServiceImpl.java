package com.cn.member.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.Role;
import com.cn.common.domain.util.Page;
import com.cn.common.domain.util.PageUtil;
import com.cn.member.mapper.system.RoleMapper;
import com.cn.member.service.system.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> searchAllRole() {
		
		return roleMapper.searchAllRole();
	}

	@Override
	public Page<Role> listRole(Map<String, Object> map) {
		PageUtil.setStartEnd(map);
		List<Role> roles = roleMapper.list(map);
		Page<Role> page = new Page<Role>();
		PageUtil.getPageFromMap(page, map);
		page.setData(roles);
		int totalCount = roleMapper.count(map);
		page.setCount(totalCount);
		return page;
	}

	@Override
	public void insertRole(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.update(role);
	}

	@Override
	public void deleteRole(String id) {
		roleMapper.delete(id);
	}

}
