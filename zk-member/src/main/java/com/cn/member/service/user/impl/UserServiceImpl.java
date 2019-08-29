package com.cn.member.service.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.user.User;
import com.cn.common.domain.util.Page;
import com.cn.common.domain.util.PageUtil;
import com.cn.member.mapper.user.UserMapper;
import com.cn.member.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Override
	public User getUser(String userCode) {
		
		return userMapper.getUser(userCode);
	}

	@Override
	public List<User> searchAllUser() {
		
		return userMapper.searchAllUser();
	}

	//用户列表
	@Override
	public Page<User> listUser(Map<String, Object> map) {
		PageUtil.setStartEnd(map);
		List<User> users = userMapper.list(map);
		Page<User> page = new Page<User>();
		PageUtil.getPageFromMap(page, map);
		page.setData(users);
		int totalCount = userMapper.count(map);
		page.setCount(totalCount);
		return page;
	}

	@Override
	public void insertUser(User user) {
		userMapper.insert(user);
		
	}

	@Override
	public void updateUser(User user) {
		userMapper.update(user);
		
	}

	@Override
	public void deleteUser(String id) {
		userMapper.delete(id);
		
	}

	@Override
	public Page<User> approverPageByGroupId(Map<String, Object> map) {
		PageUtil.setStartEnd(map);
		List<User> list = userMapper.approverPageByGroupId(map);
		Page<User> page = new Page<User>();
		PageUtil.getPageFromMap(page, map);
		page.setData(list);
		int totalCount = userMapper.approverCount(map);
		page.setCount(totalCount);
		return page;
	}

}
