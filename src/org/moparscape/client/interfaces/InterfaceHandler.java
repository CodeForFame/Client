package org.moparscape.client.interfaces;

import java.util.ArrayList;

public class InterfaceHandler {
	private static int mouseX = 0;
	private static int mouseY = 0;
	private static int mouseClick = 0;
	public static ArrayList<InterfaceGUI> guis = new ArrayList<InterfaceGUI>();
	public static void mouseMove(int mouseX, int mouseY) {
		InterfaceHandler.mouseX = mouseX;
		InterfaceHandler.mouseY = mouseY;
		for(InterfaceGUI i : guis) {
			i.handleMouseMove(mouseX, mouseY);
		}
	}
	public static boolean insideAnInterface(int mouseX, int mouseY) {
		boolean returnval = false;
		for(InterfaceGUI i : guis) {
			if(i.isVisible) {
				if(mouseX >= i.startX && mouseX <= i.startX+i.width && mouseY >= i.startY && mouseY <= i.startY + i.height) {
					returnval = true;
				}
				else {
					if(i.outsideclick != null)
						i.outsideclick.run();
				}
			}
		}
		
		return returnval;
	}
	public static void mouseClicked(int click) {
		InterfaceHandler.mouseClick = click;
		for(InterfaceGUI i : guis) {
			if(i.isVisible) {
				i.handleClick(mouseClick, mouseX, mouseY);
			}
		}
	}
	/**
	 * Called by the game loop.
	 */
	public static void tick() {
		for(InterfaceGUI i : guis) {
			i.drawOnScreen();
		}
	}
}
