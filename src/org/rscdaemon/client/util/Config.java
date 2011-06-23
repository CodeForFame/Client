package org.rscdaemon.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.rscdaemon.client.mudclient;

public class Config {
	public static String SERVER_IP = "localhost", CONF_DIR =  System.getProperty("user.home") + File.separator + ".moparscape" + File.separator + "devCache", MEDIA_DIR = CONF_DIR;
	public static int SERVER_PORT = 43595, MOVIE_FPS = 5;
	public static long START_TIME = System.currentTimeMillis();

	
	public static final int CLIENT_VERSION = 37;
	
	/**
	 * Loads the configuration file config.properties
	 * @return Properties loaded from the configuration file
	 */
	public static Properties loadConfig() {
		File f = new File(CONF_DIR + File.separator + "config.properties");
		if(!f.exists())
			try {
				f.createNewFile();
				
			} catch (IOException e1) {
				System.out.println("Unable to create configuration file!");
			}
		
		Properties defaultProps = new Properties();
		try {
			defaultProps.load(new FileInputStream(CONF_DIR + File.separator + "config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultProps;
	}
	/**
	 * Stores information into the properties + writes it to the configuration file
	 * @param key 
	 * @param value
	 */
	public static void storeConfig(String key, String value) {
		Properties config = mudclient.config;
		config.setProperty(key, value);
		try {
			FileOutputStream out = new FileOutputStream(CONF_DIR + File.separator + "config.properties");
			config.store(out,"RSCA configuration file");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
