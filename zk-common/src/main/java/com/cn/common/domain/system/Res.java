package com.cn.common.domain.system;

import java.util.List;

import com.cn.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Res extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer resId;
	
	private String resName;
	
	private Integer resType;
	
	private String url;
	
	private String resLogo;
	
	private String parentId;
	
	private Integer resOrder;
	
	private Integer resLevel;
	
	private String isSys;
	
	private boolean check;
	
	private List<Res> ress;
	
}
