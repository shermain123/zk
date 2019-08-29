package com.cn.member.web.controller.user;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.common.domain.system.Res;
import com.cn.common.domain.system.Role;
import com.cn.common.domain.system.UserRole;
import com.cn.common.domain.user.User;
import com.cn.common.domain.util.Constants;
import com.cn.common.domain.util.Page;
import com.cn.member.service.system.ResourceService;
import com.cn.member.service.system.RoleCacheService;
import com.cn.member.service.system.RoleService;
import com.cn.member.service.system.UserRoleService;
import com.cn.member.service.user.UserCacheService;
import com.cn.member.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "userCacheService")
	private UserCacheService userCacheService;
	
	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name = "resourceService")
	private ResourceService resourceService;
	
	@Resource(name = "roleCacheService")
	private RoleCacheService roleCacheService;
	
	@Resource(name = "roleService")
	private RoleService roleService;
	
	
	/**
	 * 退出
	 * */
	@RequestMapping("/logout")
	public void logout(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.logout();
		}
	}
	
	/**
	 * 登录超时
	 * */
	@RequestMapping("/dologin")
	public String doLogin(){
		return "user/dologin";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user,Model model,HttpSession session,boolean rememberme){
		//从SecurityUtils中创建一个 Subject
		Subject subject = SecurityUtils.getSubject();
		//在认证提交前准备token(令牌)
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserCode(),user.getPassWord());
		//设置登录时记住用户
		token.setRememberMe(rememberme);
		try{
			subject.login(token);
			//从缓存里查询用户名没有从数据库里查询放入缓存
			User userSys = userCacheService.getUserByCode(user.getUserCode());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userSys.getId());
			List<UserRole> urs = userRoleService.searchUserRole(map);
			
			//判断用户是为空，如果不为空把用户的权限取出来存入session里
			String roleId = new String();
			if (urs != null && urs.size() > 0) {
				UserRole userRole = urs.get(0);
				roleId = userRole.getRoleId();
				if (StringUtils.isNotBlank(roleId)) {
					//根剧用户的roleId 去缓存里拿权限 如果缓存里没有权限会从权限表里拿权限
					Role role = roleCacheService.getRoleByKey(roleId);
					int flag = role.getFlag();
					if (0 == flag) {
						roleId = "";
					}
				}

			}
			//将权限存入session
			session.setAttribute("roleId", roleId);
			return "redirect:/user/main";
		}catch(AuthenticationException ah){
			ah.printStackTrace();
			//发生异常清空token里的用户名和密码
			token.clear();
			model.addAttribute("error", "用户名或密码错误");
			return "login";
		}
	}
	
	/**主框架*/
	@RequestMapping("/main")
	public String main(Model model,HttpSession session,@RequestParam(value = "resId" , required = false)String resId){
		User user = userCacheService.getCurrentUser();
		model.addAttribute("userName", user.getUserName());
		//登录时将用户权限存入session中, 从session拿权限
		String roleId = session.getAttribute("roleId").toString();
		//session 里目前没存roleId 所以先写死
		//String roleId = "1";
		if(StringUtils.isNotBlank(roleId)){
			List<Res> topList = new ArrayList<Res>();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("roleId", roleId);
			map.put("resLevel", 2);
			//获取所有的选项卡
			topList = resourceService.searchResource(map);
			
			if(!StringUtils.isNotBlank(resId)){
				if(topList != null && topList.size() > 0){
					resId = String.valueOf(topList.get(0).getResId());
				}
			}
			
			model.addAttribute("topList", topList);
			model.addAttribute("resId", resId);
			List<Res> list = new ArrayList<Res>();
			
			//查询所有菜单
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("roleId", roleId);
			map1.put("resLevel", 3);
			map1.put("parentId", resId);
			List<Res> list1 = resourceService.searchResource(map1);
			if(list1 != null && list1.size() > 0){
				for(Res res : list1){
					Map<String,Object> map2 = new HashMap<String,Object>();
					map2.put("roleId", roleId);
					map2.put("resLevel", 4);
					map2.put("parentId", res.getResId());
					//根据每个菜单查出下面所有子菜单
					List<Res> list2 = resourceService.searchResource(map2);
					if(list2 != null && list2.size() > 0){
						res.setRess(list2);
						list.add(res);
					}
				}
			}
			model.addAttribute("list", list);
			
			model.addAttribute("firstResLogo", list.get(0).getRess().get(0).getResLogo());
			model.addAttribute("firstResName", list.get(0).getRess().get(0).getResName());
			model.addAttribute("firstResUrl", list.get(0).getRess().get(0).getUrl());
			model.addAttribute("firstResId", list.get(0).getRess().get(0).getResId());
		}
		return "user/main";
	}
	
	@RequestMapping("/userList")
	public String userList(){
		return "user/userList";
	}
	
	@RequestMapping("/userPage")
	@ResponseBody
	public Page<User> userPage(
			Model model,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false, defaultValue = Constants.PAGE_SIZE) String limit)
			throws UnsupportedEncodingException {

		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userCode)) {
			map.put("userCode", URLDecoder.decode(userCode, "utf-8"));
			model.addAttribute("userCode", userCode);
		}
		if (StringUtils.isNotBlank(userName)) {
			map.put("userName", URLDecoder.decode(userName, "utf-8"));
			model.addAttribute("userName", userName);
		}

		map.put("orderby", "update_time");
		map.put("order", "desc");
		map.put("page", page);
		map.put("limit", limit);

		Page<User> pages = userService.listUser(map);
		return pages;
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(Model model) {
		List<Role> roleList  = roleService.searchAllRole();
		model.addAttribute("roleList", roleList);
		return "/user/addUser";
	}

	@RequestMapping("/userAdd")
	@ResponseBody
	public String userAdd(
			Model model,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "passWord", required = false) String passWord,
			@RequestParam(value = "deptId", required = false) String deptId) {

		User user = userCacheService.getUserByCode(userCode);
		if (user != null) {// 判断usercode是否可用
			return "{\"result\":\"false\"}";
		} else {
			user = new User();
			user.setId(RandomStringUtils
					.randomAlphanumeric(Constants.KEY_LENGTH));
			user.setUserCode(userCode);
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setIsSys("0");
			user.setDeptId("");
			userCacheService.addUser(user);

			this.addUserRole(roleId, user.getId());
			return "{\"result\":\"true\"}";
		}
	}

	private void addUserRole(String roleId, String userId) {
		UserRole userRole = new UserRole();
		userRole.setId(RandomStringUtils
				.randomAlphanumeric(Constants.KEY_LENGTH));
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRoleService.insertUserRole(userRole);
	}

	@RequestMapping("/toEditUser")
	public String toUserUpd(Model model,
			@RequestParam(value = "id", required = false) String id) {
		User user = userCacheService.getUserByCode(id);
		model.addAttribute("user", user);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		List<UserRole> urs = userRoleService.searchUserRole(map);
		UserRole userRole = new UserRole();
		if (urs != null && urs.size() > 0) {
			userRole = urs.get(0);
		}
		
		List<Role> roleList = roleService.searchAllRole();
		model.addAttribute("roleList", roleList);
		
		model.addAttribute("userRole", userRole);
		return "/user/editUser";
	}

	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "userName", required = false) String userName) {

		User user = userCacheService.getUserByCode(id);
		user.setUserName(userName);
		userCacheService.updateUser(user);

		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			userRoleService.deleteUserRoleByMap(map);

			this.addUserRole(roleId, user.getId());
		}

		return "{\"result\":\"true\"}";
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(Model model,
			@RequestParam(value = "id", required = false) String id) {

		User user = userCacheService.getUserByCode(id);
		String isSys = user.getIsSys();
		if ("0".equals(isSys)) {
			userCacheService.deleteUser(user);

			if (user != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				userRoleService.deleteUserRoleByMap(map);
			}
			return "{\"result\":\"true\"}";
		} else {// 系统用户无法删除
			return "{\"result\":\"false\"}";
		}
	}

	@RequestMapping("/toResetUserPsd")
	public String toResetUserPsd(Model model,
			@RequestParam(value = "id", required = false) String id) {
		User user = userCacheService.getUserByCode(id);
		model.addAttribute("user", user);

		return "/user/resetUserPsd";
	}

	@RequestMapping("/resetUserPsd")
	@ResponseBody
	public String resetUserPsd(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "passWord", required = false) String passWord) {
		User user = userCacheService.getUserByCode(id);
		user.setPassWord(passWord);
		userCacheService.updateUser(user);

		return "{\"result\":\"true\"}";
	}

	@RequestMapping("/toResetPsd")
	public String toResetPsd(Model model) {
		String curUserId = userCacheService.getCurrentUserId();
		User user = userCacheService.getUserByCode(curUserId);
		model.addAttribute("user", user);

		return "/user/resetPsd";
	}

	@RequestMapping("/resetPsd")
	@ResponseBody
	public String resetPsd(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "passWord", required = false) String passWord) {
		User user = userCacheService.getUserByCode(id);
		user.setPassWord(passWord);
		userCacheService.updateUser(user);

		return "{\"result\":\"true\"}";
	}
}
