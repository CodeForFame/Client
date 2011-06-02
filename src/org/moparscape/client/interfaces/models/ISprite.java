package org.moparscape.client.interfaces.models;

import java.awt.Color;

import org.moparscape.client.GameImageMiddleMan;
import org.moparscape.client.entityhandling.EntityHandler;
import org.moparscape.client.interfaces.InterfaceGUI;

public class ISprite extends RSGuiModels {
	private int x;
	private int y;
	private int startX;
	private int startY;
	private int width = 48;
	private int height = 32;
	private Callback callback;
	private boolean isHovered = false;
	public int hoverColor = Color.yellow.hashCode();
	public int backgroundColor = 0x463d2e;
	public int borderColor = Color.white.hashCode();
	public int textColor = Color.white.hashCode();
	private InterfaceGUI parent;
	public String label = "";
	public int spriteID;
	public long id = -1;
	public HoverCallback hoverCallback = null;
	private boolean hoveredOut = true;
	public ISprite(int spriteID, int x, int y, Callback callback, int startX, int startY, InterfaceGUI parent) {
		this.spriteID = spriteID;
		this.x = x;
		this.y = y;
		this.callback = callback;
		this.startX = startX;
		this.startY = startY;
		this.parent = parent;
	}
	public boolean checkClick(int mouseX, int mouseY) {
		if(((mouseX - startX) >= x) && ((mouseX - startX) <= x+width) && ((mouseY - startY) <= y+height) && ((mouseY - startY) >= y)) {
			callback.run(parent, this);
			return true;
		}
		return false;
	}
	public void handleMouseMove(int mouseX, int mouseY) {
		if(((mouseX - startX) >= x) && ((mouseX - startX) <= x+width) && ((mouseY - startY) <= y+height) && ((mouseY - startY) >= y)) {
			isHovered = true;
			hoveredOut = true;
			return;
		}
		isHovered = false;
	}
	public void drawMe(GameImageMiddleMan g) {
		if(hoverCallback != null) 
			if(isHovered) {
				hoverCallback.hoverin(this);
				}
			else if(hoveredOut) {
				hoverCallback.hoverout(this);
				hoveredOut = false;
			}
		g.spriteClip4(startX + x,  startY + y, 48, 32, 2150 + EntityHandler.getItemDef(spriteID).getSprite(), EntityHandler.getItemDef(spriteID).getPictureMask(), 0, 0, false);
	}
 }
