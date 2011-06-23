package org.rscdaemon.client.model;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.rscdaemon.client.mudclient;

public class Resolutions {
	private static final long serialVersionUID = 6524477729282144856L;
	public final static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public final static DisplayMode oldDisplayMode = device.getDisplayMode();
	public static int resolutionSetting = Display.findDisplayMode();
	public static DisplayMode previousDisplayMode = Display.displaymodes.get(resolutionSetting);
	public int refreshSetting = 0;

	public static boolean fs = false;
	public String getResolution() {
		return Display.displaymodes.get(resolutionSetting).getWidth() + "x"+ Display.displaymodes.get(resolutionSetting).getHeight();
	}
	public void findNextResolution() {
		int i = resolutionSetting;
		while(true) {

			if(Display.displaymodes.get(i).getWidth() != previousDisplayMode.getWidth() || Display.displaymodes.get(i).getHeight() != previousDisplayMode.getHeight() && Display.displaymodes.get(i).getRefreshRate() != previousDisplayMode.getRefreshRate()) {
				previousDisplayMode = Display.displaymodes.get(i);
				resolutionSetting = i;
				System.out.println("resolutionSetting: " + resolutionSetting);
				break;
			}
			i++;
			if(i >= Display.displaymodes.size()) {
				i = 0;
				continue;
			}

		}
			
	}
	public void findSameResolution() {
		int i = 0;
		while(true) {
			if(Display.displaymodes.get(i).getRefreshRate() == Integer.valueOf(mudclient.config.getProperty("refreshRate"))) {
				resolutionSetting = i;
				break;
			}
			i++;
			if(i >= Display.displaymodes.size()) {
				i = 0;
				continue;
			}
		}	
	}
	public String getRefreshRate() {
		return "" + oldDisplayMode.getRefreshRate();
	}
}
