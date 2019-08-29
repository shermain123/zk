package com.cn.member.service.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.Role;
import com.cn.common.domain.util.Page;


public interface RoleService {

	List<Role> searchAllRole();

	Page<Role> listRole(Map<String, Object> map);

	void insertRole(Role role);

	void updateRole(Role role);
	
	void deleteRole(String id);
}
