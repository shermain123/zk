package com.cn.member.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.cn.common.domain.system.Res;
import com.cn.common.domain.system.UserRole;
import com.cn.common.domain.user.User;
import com.cn.member.mapper.system.UserRoleMapper;
import com.cn.member.service.system.ResourceService;
import com.cn.member.service.user.UserCacheService;
import com.cn.member.service.user.UserService;


public class MyShiroRealm extends AuthorizingRealm {

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "userRoleMapper")
	private UserRoleMapper userRoleMapper;
	
	@Resource(name = "resourceService")
	private ResourceService resourceService;
	
	@Resource(name = "userCacheService")
	private UserCacheService userCacheService;

	/**
	 * shiro的权限验证，在页面上用标签判断显示或隐藏
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("————权限认证————");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//User userInfo = (User) principals.getPrimaryPrincipal();
		String userCode = (String) super.getAvailablePrincipal(principals);
		User userInfo = userCacheService.getUserByCode(userCode);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userInfo.getId());
		List<UserRole> urs = userRoleMapper.searchUserRole(map);
		String roleId = new String();
		if(urs != null && urs.size() > 0){
			UserRole userRole = urs.get(0);
			roleId = userRole.getRoleId();
		}
		//假设有权限
		//roleId = "aa";
		
		List<String> permissions = new ArrayList<String>();
		
		//权限设置 将所有按钮res_logo存入 authorizationInfo 页面通过 shiro标签控制显示按钮 <shiro:hasPermission name="resadd">
		if(StringUtils.isNotBlank(roleId)){
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("roleId", roleId);
			map1.put("resLevel", 5);
			List<Res> list = resourceService.searchResource(map1);
			if(list != null && list.size()>0){
				for(Res res : list){
					permissions.add(res.getResLogo());
				}
			}
		}
		//给当前用户设置权限 
		authorizationInfo.addStringPermissions(permissions);
		return authorizationInfo;
	}

	/**
	 * shiro的登录验证，当调用SecurityUtils.getSubject().login(new UsernamePasswordToken(userCode,userPassword));
	 * 会调用该方法，内部去判断用户名和密码是否正确
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("————身份认证方法————");
		//获取用户的输入的账号.
		String userCode = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		 //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User user = userService.getUser(userCode);
		System.out.println("--->>user="+user);
		if(user == null){
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserCode(),user.getPassWord(),user.getUserName());
		return authenticationInfo;
	}
}
