package com.cn.member.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.system.Res;
import com.cn.member.mapper.BaseMapper;


public interface ResourceMapper extends BaseMapper<Res, String> {

	List<Res> searchAllResource(Map<String,Object> map);
	
	List<Res> searchResource(Map<String,Object> map);
	
	/**
	 * 调用存储过程 树排序
	 * */
	void callResource(int id);
	
	Integer getMaxId();
}
