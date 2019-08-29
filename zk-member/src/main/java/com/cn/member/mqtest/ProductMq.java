package com.cn.member.mqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ActivateMQ ��Ե� ������
 * */
public class ProductMq {
	
	public static final String USERNAME = "admin"; 
	public static final String PASSWORD = "admin"; 
	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String QUEUENAME = "my-queue"; //
	
	public static void main(String[] args) throws JMSException {
		start();
	}
	
	public static void start() throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(QUEUENAME);
		MessageProducer createProducer = session.createProducer(queue);
		
		for(int i=1 ;i <= 5; i++){
			TextMessage textMessage = session.createTextMessage("this is MQ i=" + i);
			createProducer.send(textMessage);
		}
		
		session.close();
		connection.close();
	}
	
}
