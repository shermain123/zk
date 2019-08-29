package com.cn.member.mqtest;

import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

/**
 * 延迟消费mq(消费者)
 * */
public class TestReceiverMq {

	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String QUEUENAME = "test-queue"; 
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKERURL);
		// 通过工厂创建一个连接
		Connection connection = factory.createConnection();
		// 启动连接
		connection.start();
		// 创建一个session会话 事务 自动ack
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
		// 创建一个消息队列
		Destination destination = session.createQueue(QUEUENAME);
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				try {
					System.out.println("receive message ：" + ((TextMessage) message).getText());
					message.acknowledge();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		new CountDownLatch(1).await();

	}
}
