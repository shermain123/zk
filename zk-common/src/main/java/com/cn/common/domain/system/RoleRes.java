package com.cn.common.domain.system;

import java.io.Serializable;

import com.cn.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRes extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String resId;
	
	private String roleId;

}
