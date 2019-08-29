package com.cn.common.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String updateId;
	private Date updateTime;
	private int flag;
	
}
