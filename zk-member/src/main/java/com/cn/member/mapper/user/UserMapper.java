package com.cn.member.mapper.user;

import java.util.List;
import java.util.Map;

import com.cn.common.domain.user.User;
import com.cn.member.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User,String> {

	User getUser(String userCode);
	
	List<User> searchAllUser();
	
	List<User> approverPageByGroupId(Map<String,Object> map);
	
	int approverCount(Map<String,Object> map);
	
}
