package com.cn.member.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.Res;
import com.cn.member.mapper.system.ResourceMapper;
import com.cn.member.mapper.system.TemlstMapper;
import com.cn.member.service.system.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@Resource(name = "resourceMapper")
	private ResourceMapper resourceMapper;
	
	@Resource(name = "temlstMapper")
	private TemlstMapper temlstMapper;
	
	@Override
	public List<Res> searchAllResource(Map<String, Object> map) {
		return resourceMapper.searchAllResource(map);
	}
	
	@Override
	public List<Res> searchResource(Map<String, Object> map) {
		return resourceMapper.searchResource(map);
	}
	
	@Override
	public void inserRes(Res res) {
		resourceMapper.insert(res);
		//调用存储过程重新序列化等级
		resourceMapper.callResource(0);
	}
	
	@Override
	public void updateRes(Res res) {
		resourceMapper.update(res);
	}
	
	@Override
	public void deleteRes(String id) {
		resourceMapper.delete(id);
		resourceMapper.callResource(0);
	}
	
	@Override
	public Integer getMaxId() {
		return resourceMapper.getMaxId();
	}

}
