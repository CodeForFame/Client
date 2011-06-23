package org.rscdaemon.client;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;

public class MessageQueue {

	protected MessageQueue() {
	}

	private static MessageQueue mq = null;
	public static List<Message> list = Collections
			.synchronizedList(new LinkedList<Message>());
	public static final int TIME_DISPLAYED = 10000;
	public static final int BIG_TIME_DISPLAYED = 3500;

	public static boolean isFinished(Message mes) {
		if(mes.isBIG)
			return (System.currentTimeMillis() - BIG_TIME_DISPLAYED) > mes.time;
		return (System.currentTimeMillis() - TIME_DISPLAYED) > mes.time;
	}

	public static List<Message> getList() {
		return list;
	}

	public static void checkProcessMessages() {
		try {
			synchronized (list) {
				for (Message m : list) {
					if (isFinished(m)) {
						synchronized (list) {
							getList().remove(m);
						}
					}
				}
			}
		} catch (ConcurrentModificationException cme) {

		}
	}

	public static MessageQueue getQueue() {
		if (mq == null)
			mq = new MessageQueue();
		return mq;
	}

	public static boolean hasMessages() {
		return getList().size() > 0;
	}

	public static void addMessage(Message m) {
		synchronized (list) {
			getList().add(m);
		}
	}

}
