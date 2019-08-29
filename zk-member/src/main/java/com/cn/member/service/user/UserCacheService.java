package com.cn.member.service.user;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.user.User;
import com.cn.common.domain.util.Page;


public interface UserCacheService {

	void initUser();

	User getUserByCode(String code);

	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);

	User getCurrentUser();

	String getCurrentUserId();
	
}
