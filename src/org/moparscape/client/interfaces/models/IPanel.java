package org.moparscape.client.interfaces.models;

import java.awt.Color;

import org.moparscape.client.GameImageMiddleMan;
import org.moparscape.client.interfaces.InterfaceGUI;

public class IPanel extends RSGuiModels {
	private int x;
	private int y;
	private int startX;
	private int startY;
	private int width;
	private int height;
	private boolean isHovered = false;
	public int hoverColor = 0x463d2e;
	public int backgroundColor = 0x463d2e;
	public int borderColor = Color.black.hashCode();
	public int textColor = Color.white.hashCode();
	public long id = -1;
	public HoverCallback hoverCallback = null;
	private InterfaceGUI parent;
	public IPanel(int x, int y, int width, int height, int startX, int startY, InterfaceGUI parent) {
		this.x = x;
		this.y = y;
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.parent = parent;
	}
	public boolean checkClick(int mouseX, int mouseY) {
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
		if(hoverCallback != null) 
			if(isHovered)
				hoverCallback.hoverin(this);
			else
				hoverCallback.hoverout(this);
		
		
		g.drawBox(startX + x, startY + y, width, height, isHovered ? hoverColor : backgroundColor);
		g.drawBoxEdge(startX + x, startY + y, width, height, borderColor);

	}
 }
