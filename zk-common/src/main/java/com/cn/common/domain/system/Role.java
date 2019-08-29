package com.cn.common.domain.system;

import com.cn.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String roleName;
	
	private String description;
	
	private String roleLogo;
	
	private String isSys;
	
}
