package org.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	public static void main(String[] args) throws JMSException, InterruptedException {
		ConnectionFactory f = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
		Connection con = f.createConnection();
		con.start();
		Session session = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination des = session.createQueue("test1");
		MessageProducer produce = session.createProducer(des);
		for (int i = 0; i < 20; i++) {
			produce.send(session.createTextMessage("测试消息队列的传输::" + i));
		}
		produce.send(session.createTextMessage("close"));
		con.close();
		System.exit(1);
	}
}
