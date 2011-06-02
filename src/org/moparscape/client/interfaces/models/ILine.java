package org.moparscape.client.interfaces.models;

import org.moparscape.client.GameImageMiddleMan;
import org.moparscape.client.interfaces.InterfaceGUI;

public class ILine extends RSGuiModels {
	private int x;
	private int y;
	private int startX;
	private int startY;
	private int width;
	public int borderColor = 0x6b5e53;
	private InterfaceGUI parent;
	private boolean isHorizontal = false;
	public long id = -1;
	public ILine(int x, int y, int width, boolean horizontal, int startX, int startY, InterfaceGUI parent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.isHorizontal = horizontal;
		this.startX = startX;
		this.startY = startY;
		this.parent = parent;

		
	}
	public boolean checkClick(int mouseX, int mouseY) {
		return false;
	}
	public void handleMouseMove(int mouseX, int mouseY) {
	}
	public void drawMe(GameImageMiddleMan g) {
		if(isHorizontal)
			g.drawLineX(startX+x, startY+y, width, borderColor);
		else
			g.drawLineY(startX+x, startY+y, width, borderColor);
			
	}
 }
