package com.cn.common.domain.user;



import com.cn.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userCode;

    private String userName;

    private String passWord;

    private String isSys;

    private String deptId;

    private String roleName;
}
