package com.cn.member.service.user;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.user.User;
import com.cn.common.domain.util.Page;

public interface UserService {

	User getUser(String userCode);
	
	List<User> searchAllUser();
	
	Page<User> listUser(Map<String, Object> map);

	void insertUser(User user);

	void updateUser(User user);

	void deleteUser(String id);
	
	Page<User> approverPageByGroupId(Map<String, Object> map);
	
	
}
