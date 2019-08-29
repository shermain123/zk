package com.cn.member.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.RoleRes;
import com.cn.member.mapper.BaseMapper;

public interface RoleResMapper extends BaseMapper<RoleRes, String> {

	int insertBatch(List<RoleRes> list);

	int deleteRoleResByMap(Map<String, Object> map);

	List<RoleRes> searchRoleRes(Map<String, Object> map);
}
