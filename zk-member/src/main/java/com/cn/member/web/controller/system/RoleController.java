package com.cn.member.web.controller.system;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.common.domain.system.Res;
import com.cn.common.domain.system.Role;
import com.cn.common.domain.system.RoleRes;
import com.cn.common.domain.util.Constants;
import com.cn.common.domain.util.Page;
import com.cn.member.service.system.ResourceService;
import com.cn.member.service.system.RoleCacheService;
import com.cn.member.service.system.RoleResService;
import com.cn.member.service.system.RoleService;
import com.cn.member.service.system.UserRoleService;
import com.cn.member.service.user.UserCacheService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource(name = "roleService")
	private RoleService roleService;
	
	@Resource(name = "roleCacheService")
	private RoleCacheService roleCacheService;
	
	
	@Resource(name = "userCacheService")
	private UserCacheService userCacheService;

	@Resource(name = "roleResService")
	private RoleResService roleResService;

	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;

	@Resource(name = "resourceService")
	private ResourceService resourceService;
	
	@RequestMapping("/roleList")
	public String roleList(){
		return "/role/roleList";
	}
	
	@RequestMapping("/rolePage")
	@ResponseBody
	public Page<Role> rolePage(
			Model model,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false, defaultValue = Constants.PAGE_SIZE) String limit) throws UnsupportedEncodingException {

		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(roleName)) {
			map.put("roleName", URLDecoder.decode(roleName, "utf-8"));
			model.addAttribute("roleName", roleName);
		}

		map.put("orderby", "update_time");
		map.put("order", "desc");
		map.put("page", page);
		map.put("limit", limit);

		Page<Role> pageRole = roleService.listRole(map);
		return pageRole;
	}

	@RequestMapping("/toAddRole")
	public String toAddRole() {
		return "/role/addRole";
	}

	@RequestMapping("/roleAdd")
	@ResponseBody
	public String roleAdd(
			Model model,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "roleLogo", required = false) String roleLogo,
			@RequestParam(value = "description", required = false) String description) {
		Role role = roleCacheService.getRoleByKey(roleLogo);
		if (role != null) {// 判断roleLogo是否可用
			return "{\"result\":\"false\"}";
		} else {
			role = new Role();
			role.setId(RandomStringUtils
					.randomAlphanumeric(Constants.KEY_LENGTH));
			role.setRoleName(roleName);
			role.setRoleLogo(roleLogo);
			role.setDescription(description);
			role.setIsSys("0");
			role.setFlag(1);
			role.setUpdateId(userCacheService.getCurrentUserId());
			roleCacheService.addRole(role);
			return "{\"result\":\"true\"}";
		}
	}

	@RequestMapping("/toEditRole")
	public String toEditRole(Model model,
			@RequestParam(value = "id", required = false) String id) {
		Role role = roleCacheService.getRoleByKey(id);
		model.addAttribute("role", role);

		return "/role/editRole";
	}

	@RequestMapping("/editRole")
	@ResponseBody
	public String editRole(
			Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "flag", required = false) String flag,
			@RequestParam(value = "description", required = false) String description) {

		Role role = roleCacheService.getRoleByKey(id);
		role.setRoleName(roleName);
		if (StringUtils.isNotBlank(flag)) {
			role.setFlag(Integer.parseInt(flag));
		}
		role.setDescription(description);
		role.setUpdateId(userCacheService.getCurrentUserId());
		roleCacheService.updateRole(role);
		return "{\"result\":\"true\"}";
	}

	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(Model model,
			@RequestParam(value = "id", required = false) String id) {

		Role role = roleCacheService.getRoleByKey(id);
		String isSys = role.getIsSys();
		if ("0".equals(isSys)) {
			roleCacheService.deleteRole(role);
			// 删除角色对应的资源
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", id);
			roleResService.deleteRoleResByMap(map);
			// 删除角色对应的人员
			userRoleService.deleteUserRoleByMap(map);
			return "{\"result\":\"true\"}";
		} else {// 系统角色无法删除
			return "{\"result\":\"false\"}";
		}
	}

	@RequestMapping("/toSetRoleRes")
	public String toSetRoleRes(
			@RequestParam(value = "id", required = false) String id,Model model) {

		Role role = roleCacheService.getRoleByKey(id);
		List<Res> resList = resourceService.searchAllResource(null);

		List<Integer> checkResIdList = new ArrayList<Integer>();
		if (role != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", role.getId());
			List<RoleRes> checkList = roleResService.searchRoleRes(map);
			if (checkList != null) {
				for (RoleRes roleRes : checkList) {
					checkResIdList.add(Integer.parseInt(roleRes.getResId()));
				}
			}
		}

		for (Res res : resList) {
			if (checkResIdList.contains(res.getResId())) {
				res.setCheck(true);
			} else {
				res.setCheck(false);
			}
		}

		JSONArray jsonarray = JSONArray.fromObject(resList);
		model.addAttribute("roleId", id);
		model.addAttribute("listJSON", jsonarray.toString());
		return "/role/roleSet";
	}

	@RequestMapping("/setRoleRes")
	@ResponseBody
	public String setRoleRes(Model model,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "resIds", required = false) String resIds) {
		if (StringUtils.isNotBlank(resIds)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			try {
				roleResService.deleteRoleResByMap(map);

				String[] arrayResId = resIds.split(",");
				List<RoleRes> list = new ArrayList<RoleRes>();
				for (int i = 0; i < arrayResId.length; i++) {
					RoleRes roleRes = new RoleRes();
					roleRes.setId(RandomStringUtils
							.randomAlphanumeric(Constants.KEY_LENGTH));
					roleRes.setRoleId(roleId);
					roleRes.setResId(arrayResId[i]);
					list.add(roleRes);
				}

				roleResService.insertBatch(list);

				return "{\"result\":\"true\"}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{\"result\":\"false\"}";
			}
		}
		return null;
	}
}
