package com.indus.training.service;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSReceiver {

	public void receiveMessage() {

		InitialContext wlInitCxt = null;
		QueueConnectionFactory queueConnectionFactory = null;
		QueueConnection queueConnection = null;
		QueueSession queueSession = null;
		Queue queue = null;
		QueueReceiver queueReceiver = null;
		Object objectMessage = null;

		// 1) JNDI properties.
		Hashtable<String, String> wlJndiConnProps = new Hashtable<String, String>();
		wlJndiConnProps.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		wlJndiConnProps.put(Context.PROVIDER_URL, "t3://localhost:9010");

		// 2)Create connection to JNDI directory source.
		try {
			wlInitCxt = new InitialContext(wlJndiConnProps);

			queueConnectionFactory = (QueueConnectionFactory) wlInitCxt
					.lookup("jndiIndusTrainingQueueConnectionFactory");
			queue = (Queue) wlInitCxt.lookup("jndiIndusMessagingQueue");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("JNDI API lookup failed: " + e.toString());
			System.exit(1);

		} finally {
			if (wlInitCxt != null) {

				try {
					wlInitCxt.close();
				} catch (NamingException e) {
					e.printStackTrace();

				}
			}
		}

		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create sender and text message. Send
		 * messages, varying text slightly. Send end-of-messages message.
		 * Finally, close connection.
		 */
		try {
			queueConnection = queueConnectionFactory.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			queueReceiver = queueSession.createReceiver(queue);
			queueConnection.start();
			while (true) {
				Object inMessageObj = queueReceiver.receive();
				if (inMessageObj != null) {
						objectMessage =   inMessageObj;
						System.out.println("Reading Object: "
								+ objectMessage);
					} else {
						break;
					}
				
			}

		} catch (JMSException e) {
			System.out.println("Exception occurred: " + e.toString());
		} finally {
			if (queueConnection != null) {
				try {
					queueConnection.close();
				} catch (JMSException e) {
				}
			}
		}

	}
}
