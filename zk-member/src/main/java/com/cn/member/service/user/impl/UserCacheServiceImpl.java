package com.cn.member.service.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.cn.common.domain.user.User;
import com.cn.common.domain.util.Page;
import com.cn.member.mapper.user.UserMapper;
import com.cn.member.service.user.UserCacheService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Service("userCacheService")
public class UserCacheServiceImpl implements UserCacheService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	//cache缓存
	@Resource(name = "userCache")
	private Cache userCache;
	
	//将所有user放入cache中
	@Override
	public void initUser() {
		List<User> users = userMapper.searchAllUser();
		if(users != null){
			for(User user : users){
				Element element = new Element(user.getUserCode(),user);
				userCache.put(element);
				
				Element userElement = new Element(user.getId(),user);
				userCache.put(userElement);
			}
		}
	}

	@Override
	public User getUserByCode(String code) {
		//跟据code从缓存里取用户
		Element element = userCache.get(code);
		//若是没取到重新查询用户放入cache
		if(element == null){
			initUser();
			//然后在取
			element = userCache.get(code);
		}
		
		if(element == null){
			//若还为空还回Null
			return null;
		}else{
			//反之反回用户
			User user = (User) element.getObjectValue();
			return user;
		}
	}

	@Override
	public void addUser(User user) {
		if(user != null){
			userMapper.insert(user);
			//用户存入过后把用户对象重新放入缓存
			Element element = new Element(user.getUserCode(),user);
			userCache.put(element);
			Element userElement = new Element(user.getId(),user);
			userCache.put(userElement);
		}
		
	}

	@Override
	public void updateUser(User user) {
		if(user != null){
			userMapper.update(user);
			Element element = new Element(user.getUserCode(),user);
			userCache.put(element);
			Element userElement = new Element(user.getId(),user);
			userCache.put(userElement);
		}
		
	}

	@Override
	public void deleteUser(User user) {
		if(user != null){
			userMapper.delete(user.getId());
			userCache.remove(user.getUserCode());
			userCache.remove(user.getId());
		}
		
	}

	@Override
	public User getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		String account = currentUser.getPrincipal().toString();
		return getUserByCode(account);
	}

	@Override
	public String getCurrentUserId() {
		User user = this.getCurrentUser();
		return user.getId();
	}
	
	

}
