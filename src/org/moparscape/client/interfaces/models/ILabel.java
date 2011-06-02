package org.moparscape.client.interfaces.models;
import java.awt.Color;

import org.moparscape.client.GameImageMiddleMan;
import org.moparscape.client.interfaces.InterfaceGUI;

public class ILabel extends RSGuiModels {
	public int x;
	private int y;
	private int startX;
	private int startY;
	private int width;
	private int height;
	public Callback callback;
	private boolean isHovered = false;
	public int hoverColor = 0x6b5e53;
	public int backgroundColor = 0x463d2e;
	public int borderColor = 0x463d2e;
	public int textColor = Color.white.hashCode();
	private InterfaceGUI parent;
	public String label = "";
	public long id = -1;
	private int fontnumber = 0;
	public void setTextBoldness(int boldness) {
		fontnumber = boldness;
		width = parent.textWidth(label, fontnumber)+10;
		height = 15+(fontnumber*1);
	}
	public ILabel(String text, int x, int y, Callback callback, int startX, int startY, InterfaceGUI parent, int fontNumber) {
		this.label = text;
		this.x = x;
		this.y = y;
		this.callback = callback;
		this.startX = startX;
		this.startY = startY;
		this.parent = parent;
		this.fontnumber = fontNumber;
		width = parent.textWidth(label, fontNumber)+10;
		height = 15+(fontNumber*1);
		
	}
	public boolean checkClick(int mouseX, int mouseY) {
		if(((mouseX - startX) >= x) && ((mouseX - startX) <= x+width) && ((mouseY - startY) <= y+height) && ((mouseY - startY) >= y)) {
			if(callback != null) callback.run(parent, this);
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
		g.drawBox(startX + x, startY + y, width, height, backgroundColor);
		g.drawBoxEdge(startX + x, startY + y, width, height, borderColor);
		g.drawText(label, startX + x +(width/2), startY + y + 10+(fontnumber*2), fontnumber, isHovered ? hoverColor : textColor);
	}
 }
