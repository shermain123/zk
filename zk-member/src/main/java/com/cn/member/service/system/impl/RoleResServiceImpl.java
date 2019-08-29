package com.cn.member.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.RoleRes;
import com.cn.member.mapper.system.RoleResMapper;
import com.cn.member.service.system.RoleResService;


@Service("roleResService")
public class RoleResServiceImpl implements RoleResService {

	@Resource(name = "roleResMapper")
	private RoleResMapper roleResMapper;

	@Override
	public void insertRoleRes(RoleRes roleRes) {
		roleResMapper.insert(roleRes);
	}

	@Override
	public void insertBatch(List<RoleRes> list) {
		roleResMapper.insertBatch(list);
	}
	
	@Override
	public int deleteRoleResByMap(Map<String, Object> map) {
		return roleResMapper.deleteRoleResByMap(map);
	}

	@Override
	public List<RoleRes> searchRoleRes(Map<String, Object> map) {
		return roleResMapper.searchRoleRes(map);
	}

}
