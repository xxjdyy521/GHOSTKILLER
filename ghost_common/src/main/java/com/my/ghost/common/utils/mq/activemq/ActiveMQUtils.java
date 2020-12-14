package com.my.ghost.common.utils.mq.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;
/**
 * activeMq消息发送及连接
 * @author l
 */
@Component(value = "activeMQUtils")
public class ActiveMQUtils {
	
	/**
	 * 开启/关闭连接--主动触发
	 * @param username
	 * @param password
	 * @param url
	 * @return
	 */
	public static Connection getConn(String username,String password,String url) {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				username,password,url);
		try {
			Connection connection = factory.createConnection();
			return connection;
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * QueueMsg发送
	 * @param connection
	 * @param queueName
	 * @param queueContent
	 * @return
	 */
	public static boolean sendQueueMsg(Connection connection,String queueName,String queueContent) {
		try {
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination des = session.createQueue(queueName);
			MessageProducer msgProducer = session.createProducer(des);
			/*设置持久化属性*/
			msgProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage queueMsg = session.createTextMessage();
			queueMsg.setText(queueContent);
			msgProducer.send(queueMsg);
			return true;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * TopicMsg发送
	 * @param connection
	 * @param topicName
	 * @param topicContent
	 * @return
	 */
	public static boolean sendTopicMsg(Connection connection,String topicName,String topicContent) {
		try {
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination des = session.createTopic(topicName);
			MessageProducer msgProducer = session.createProducer(des);
			/*设置持久化属性*/
			msgProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage topicMsg = session.createTextMessage();
			topicMsg.setText(topicContent);
			msgProducer.send(topicMsg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 获取queueMsg
	 * @param connection
	 * @param queueName
	 * @return
	 */
	public static void getQueueMsg(Connection connection,String queueName) {
		try {
			connection.start();
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination des = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(des);
			/* Listener和receive只能使用一个*/
			consumer.setMessageListener(new MsgListener());
			//TextMessage queueMsg = (TextMessage) consumer.receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
