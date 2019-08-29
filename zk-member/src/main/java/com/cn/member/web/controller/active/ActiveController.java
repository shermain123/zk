package com.cn.member.web.controller.active;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.domain.activemq.MessageModel;
import com.cn.member.activemq.Producer;
import com.cn.member.config.ActiveMQClient;

@RestController
@RequestMapping("/mq")
public class ActiveController {

	@Autowired
	ActiveMQClient client;
	
	@Resource(name = "producer")
	private Producer producer;
	
	@RequestMapping("/mqTest")
	public void mqTest(){
		client.send("猜猜我是谁！");
		
		
	}
	
	/**
	 * 即使发送
	 * */
	@RequestMapping("/test")
    public void test(){
        /*MessageModel messageModel = MessageModel.builder()
                .message("测试消息")
                .titile("消息000")
                .build();*/
		MessageModel model = new MessageModel();
		model.setTitile("发送一条数据");
		model.setMessage("测试数据");
		String message = JSONObject.toJSONString(model);
        // 发送消息
        producer.send(Producer.DEFAULT_QUEUE, message);
    }
 
    /**
     * 延时消息队列测试
     */
    @RequestMapping("/test2")
    public void test2(){
        /*for (int i=0;i< 20;i++){
            MessageModel messageModel = MessageModel.builder()
                    .titile("延迟10秒执行")
                    .message("测试消息" + i)
                    .build();
            // 发送延迟消息
            producer.delaySend(Producer.DEFAULT_QUEUE, messageModel, 10000L);
        }*/
    	System.out.println("---------延迟进入--------");
    	 MessageModel model = new MessageModel();
    	 model.setTitile("发送一条消息延迟10秒执行");
    	 model.setMessage("我是哈士奇");
    	 producer.delaySend(Producer.DEFAULT_QUEUE, model, 30000L);
        /*try {
            // 休眠100秒，等等消息执行
            Thread.currentThread().sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}
