package com.cn.common.domain.activemq;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息模型
 * */

@Getter
@Setter
public class MessageModel implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String titile;
    
	private String message;
	

}
