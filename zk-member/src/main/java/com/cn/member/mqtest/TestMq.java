package com.cn.member.mqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

/**
 * 延迟发送mq(生产者)
 * */
public class TestMq {
	
	public static final String USERNAME = "admin"; 
	public static final String PASSWORD = "admin"; 
	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String QUEUENAME = "test-queue"; //
	
	public static void main(String[] args) throws Exception {
		//创建连接工厂
		ConnectionFactory factory  = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,BROKERURL);
		//通过工厂创建一个连接
		Connection connection = factory.createConnection();
		//启动连接
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建一个消息队列
		Destination destination = session.createQueue(QUEUENAME);
		// 创建生产者
		MessageProducer producer = session.createProducer(destination);
		// 消息持久化
		TextMessage message = session.createTextMessage("消费信息:" + System.currentTimeMillis());
		long time = 60 * 1000 ;// 延时1min
		message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
		// 发送消息
		producer.send(message);
		session.commit();
		producer.close();
		session.close();
		connection.close();

	}
	
}
