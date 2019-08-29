package com.cn.member.mqtest;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * */
public class ReceiverMq {

	public static final String USERNAME = "admin"; 
	public static final String PASSWORD = "admin"; 
	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String QUEUENAME = "my-queue"; 
	
	public static void main(String[] args) throws JMSException, IOException {
		start();
	}
	
	public static void start() throws JMSException, IOException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(QUEUENAME);
		
		MessageConsumer consumer = session.createConsumer(queue);
		
		consumer.setMessageListener(new MessageListener() {
			             @Override
			             public void onMessage(Message message) {
			                 TextMessage textMessage = (TextMessage) message;
			                 try {
			                     System.out.println("消费1--接收到消息:"+textMessage.getText());
			                 } catch (JMSException e) {
			                     e.printStackTrace();
			                 }
			             }
			         });
		//8.等待键盘输入
		         System.in.read();
		         //9.关闭资源
		         consumer.close();
		/*while(true){
			Message receiver = consumer.receive();
			TextMessage textMessage = (TextMessage) receiver;
			
			if(textMessage != null){
				String contrant = textMessage.getText();
				System.out.println("this" + contrant);
			}else{
				System.out.println("没有消息");
				break;
			}
			
		}*/
		session.close();
		connection.close();
	}
}
