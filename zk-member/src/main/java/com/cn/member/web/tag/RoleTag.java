package com.cn.member.web.tag;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.common.domain.system.Role;
import com.cn.common.domain.system.RoleRes;
import com.cn.member.service.system.RoleResService;
import com.cn.member.service.system.RoleService;


public class RoleTag extends AbstractTagBase {

	private RoleService roleService;
	
	private RoleResService roleResService;

	public LinkedHashMap<String, String> getDataSource() {
		ServletContext servletContext = ((PageContext) this.getJspContext())
				.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		roleService = (RoleService) wac.getBean("roleService");
		roleResService = (RoleResService) wac.getBean("roleResService");

		List<Role> list = roleService.searchAllRole();
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		if (list != null && list.size() > 0) {
			for (Role role : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleId", role.getId());
				List<RoleRes> roleRes = roleResService.searchRoleRes(map);
				if(roleRes != null && roleRes.size() > 0 ){
					linkedHashMap.put(role.getId(), role.getRoleName());
				}
			}
		}

		return linkedHashMap;
	}

}