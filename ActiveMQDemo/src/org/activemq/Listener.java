package org.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Listener {

	public static void main(String[] args) throws JMSException, IOException {
		// 使用默认的用户名、密码、以及默认的端口号进行测试
		ConnectionFactory f = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
		Connection con = f.createConnection();
		// 开启连接
		con.start();
		// 创建session，用于消息传输（注意这里是非事务，若为true，则produce发送消息后需执行session的commit）
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination des = session.createQueue("test1");
		MessageConsumer mes = session.createConsumer(des);
		mes.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message mess) {
				if (mess instanceof TextMessage) {
					try {
						String body = ((TextMessage) mess).getText();
						if ("close".equals(body)) {
							con.close();
							System.exit(1);
						}
						System.out.println(body);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

}
