package com.cn.member.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.domain.activemq.MessageModel;

/**
 * 消费者
 * */
@Component
public class Consumer {

	/**
	 * 消息监听
	 * */
	@JmsListener(destination = "default.queue")
    public void receiveQueue(MessageModel message){
		//MessageModel msg = (MessageModel)message;
		/*JSONObject json = new JSONObject();
		MessageModel msg = json.parseObject(message,MessageModel.class);*/
        //System.out.println("收到消息:"+msg.getTitile()+"="+msg.getMessage());
		System.out.println("---"+message.getTitile()+"------"+message.getMessage()+"----");
    }
}
