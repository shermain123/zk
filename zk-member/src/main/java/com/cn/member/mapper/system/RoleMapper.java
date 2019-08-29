package com.cn.member.mapper.system;

import java.util.List;

import com.cn.common.domain.system.Role;
import com.cn.member.mapper.BaseMapper;


public interface RoleMapper extends BaseMapper<Role,String> {
	
	List<Role> searchAllRole();
	
}
