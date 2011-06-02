package org.moparscape.client.interfaces.models;

import java.awt.Color;

import org.moparscape.client.GameImageMiddleMan;
import org.moparscape.client.interfaces.InterfaceGUI;

public class IButton extends RSGuiModels {
	private int x;
	private int y;
	private int startX;
	private int startY;
	private int width;
	private int height;
	private Callback callback;
	private boolean isHovered = false;
	public int hoverColor = Color.yellow.hashCode();
	public int backgroundColor = 0x463d2e;
	public int borderColor = Color.white.hashCode();
	public int textColor = Color.white.hashCode();
	private InterfaceGUI parent;
	public String label = "";
	public long id = -1;
	public IButton(int x, int y, Callback callback, int width, int height, int startX, int startY, InterfaceGUI parent) {
		this.x = x;
		this.y = y;
		this.callback = callback;
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.parent = parent;
	}
	public boolean checkClick(int mouseX, int mouseY) {
		if(((mouseX - startX) >= x) && ((mouseX - startX) <= x+width) && ((mouseY - startY) <= y+height) && ((mouseY - startY) >= y)) {
			if(callback != null) 
				callback.run(parent, this);
			return true;
		}
		return false;
	}
	public void handleMouseMove(int mouseX, int mouseY) {
		if(((mouseX - startX) >= x) && ((mouseX - startX) <= x+width) && ((mouseY - startY) <= y+height) && ((mouseY - startY) >= y)) {
			isHovered = true;
			return;
		}
		isHovered = false;
	}
	public void drawMe(GameImageMiddleMan g) {
		g.drawBox(startX + x, startY + y, width, height, isHovered ? hoverColor : backgroundColor);
		g.drawBoxEdge(startX + x, startY + y, width, height, borderColor);
		g.drawText(label, startX + x +(width/2), startY + y + (height/2), 0, textColor);
	}
 }
