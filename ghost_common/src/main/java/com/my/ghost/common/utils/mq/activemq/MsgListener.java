package com.my.ghost.common.utils.mq.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("msgListener")
public class MsgListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage)message;
		try {
			System.out.println(msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
