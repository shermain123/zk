package com.cn.common.domain.cust;

import java.io.Serializable;

import com.cn.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer parentId;
	
	private String name;
	
	private String isMust;
	
	private Integer orderNum;

}
