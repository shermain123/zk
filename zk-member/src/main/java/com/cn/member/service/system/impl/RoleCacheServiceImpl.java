package com.cn.member.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.Role;
import com.cn.member.service.system.RoleCacheService;
import com.cn.member.service.system.RoleService;


@Service("roleCacheService")
public class RoleCacheServiceImpl implements RoleCacheService {

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "roleCache")
	private Cache roleCache;

	@Override
	public void initRole() {
		List<Role> list = roleService.searchAllRole();
		if (list != null) {
			for (Role role : list) {
				Element element = new Element(role.getRoleLogo(), role);
				roleCache.put(element);

				Element ResElement = new Element(role.getId(), role);
				roleCache.put(ResElement);
			}
		}
	}

	@Override
	public Role getRoleByKey(String key) {
		Element element = roleCache.get(key);
		if (element == null) {
			initRole();
			element = roleCache.get(key);
		}

		if (element == null) {
			return null;
		} else {
			Role role = (Role) element.getObjectValue();
			return role;
		}
	}

	@Override
	public void addRole(Role role) {
		if (role != null) {
			roleService.insertRole(role);
			Element element = new Element(role.getRoleLogo(), role);
			roleCache.put(element);
			Element ResElement = new Element(role.getId(), role);
			roleCache.put(ResElement);
		}

	}

	@Override
	public void updateRole(Role role) {
		if (role != null) {
			roleService.updateRole(role);
			Element element = new Element(role.getRoleLogo(), role);
			roleCache.put(element);
			Element ResElement = new Element(role.getId(), role);
			roleCache.put(ResElement);
		}

	}

	@Override
	public void deleteRole(Role role) {
		roleService.deleteRole(role.getId());
		roleCache.remove(role.getRoleLogo());
		roleCache.remove(role.getId());
	}

}
