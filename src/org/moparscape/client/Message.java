package org.moparscape.client;

/**
 * Staff Message model.
 * 
 * @author xEnt
 * 
 */
public class Message {

	public Message(String s) {
		this.message = s;
		time = System.currentTimeMillis();
	}
	
	public Message(String s, boolean big) {
		this.isBIG = big;
		this.message = s;
		time = System.currentTimeMillis();
	}

	public String message = null;
	public long time = -1;
	public boolean isBIG = false;
}
