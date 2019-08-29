package com.cn.common.domain.system;

import java.io.Serializable;

import com.cn.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String roleId;

}
