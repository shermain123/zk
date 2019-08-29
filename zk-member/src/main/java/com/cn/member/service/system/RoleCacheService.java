package com.cn.member.service.system;

import com.cn.common.domain.system.Role;

public interface RoleCacheService {

	void initRole();

	Role getRoleByKey(String key);

	void addRole(Role role);
	
	void updateRole(Role role);
	
	void deleteRole(Role role);

}
