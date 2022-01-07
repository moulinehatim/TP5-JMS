package consumer;

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
import org.apache.log4j.BasicConfigurator;

public class Consumer {

	public static void main(String[] args) throws JMSException {
		System.out.println("THIS IS CONSUMEEEEEEEEER");
		BasicConfigurator.configure();

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic("MyTopicxxx");
		MessageConsumer consumer = session.createConsumer(destination);
		connection.start();

		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {

				try {
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						String text;
						text = textMessage.getText();
						System.out.println("Recu : " + text);
					} else {
						System.out.println("Recu : " + message);
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
