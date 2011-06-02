package org.moparscape.client.model;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import org.moparscape.client.mudclient;

public class Display {
	public static ArrayList<Integer> refreshRate = new ArrayList<Integer>();
	public static ArrayList<DisplayMode> displaymodes = new ArrayList<DisplayMode>();
	static
		{
		 GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 for (GraphicsDevice device : env.getScreenDevices()) {
			 displaymodes.add(new DisplayMode(502, 382, device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
			 for(DisplayMode dm : device.getDisplayModes()) {
				 boolean found = false;
				 for(DisplayMode test : displaymodes) {
					 if(dm.getHeight() == test.getHeight() && dm.getWidth() == test.getWidth()) {
						 found = true;
					 }
				 }
				 if(!found && dm.getWidth() > 502 && dm.getHeight() > 382)
					 displaymodes.add(dm);
			 	}
		 	}
		}
	public static int findDisplayMode() {
		int i = 0;
		for(DisplayMode test : displaymodes) {
			if(mudclient.config.getProperty("height") != null && mudclient.config.getProperty("width") != null && Integer.valueOf(mudclient.config.getProperty("height")) == test.getHeight() && Integer.valueOf(mudclient.config.getProperty("width")) == test.getWidth()) {
				return i;
			}
			i++;
		}
		return 0;
	}
}
