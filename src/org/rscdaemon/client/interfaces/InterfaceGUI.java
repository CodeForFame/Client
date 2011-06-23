package org.rscdaemon.client.interfaces;

import java.util.ArrayList;

import org.rscdaemon.client.GameImage;
import org.rscdaemon.client.mudclient;
import org.rscdaemon.client.interfaces.models.Callback;
import org.rscdaemon.client.interfaces.models.IButton;
import org.rscdaemon.client.interfaces.models.ILabel;
import org.rscdaemon.client.interfaces.models.ILine;
import org.rscdaemon.client.interfaces.models.IPanel;
import org.rscdaemon.client.interfaces.models.ISprite;
import org.rscdaemon.client.interfaces.models.OnMisClickCallBack;
import org.rscdaemon.client.interfaces.models.RSGuiModels;

public class InterfaceGUI {
	//For question menu
	public String[] Questions;
	int width;
	int height;
	private mudclient mc;
	public boolean isVisible = false;
	int startX;
	int startY;
	public int backgroundColor = 0x463d2e;
	public int borderColor = 0x6b5e53;
	public ArrayList<RSGuiModels> buttons = new ArrayList<RSGuiModels>();
	public ILabel titlebar = null;
	public ILabel xbutton = null;
	public OnMisClickCallBack outsideclick = null;
	
	public InterfaceGUI(int width, int height, mudclient mc) {
		this.width = width;
		this.height = height;
		this.mc = mc;
		startX = (mc.windowWidth - width)/2;
		startY = (mc.windowHeight - height)/2+10;
		titlebar = addLabel("Titlebar", 20, -24, null, 1);
		xbutton = addLabel("x", width-23, -23, new Callback() {

			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				gui.isVisible = false;
				
			}
			
		}, 1);
	}
	public IButton addButton(int locx, int locy, Callback callback, int height, int width) {
		IButton b = new IButton(locx, locy, callback, height, width, startX, startY, this);
		buttons.add(b);
		return b;
	}
	public ILabel addLabel(String text, int locx, int locy, Callback callback, int textBoldness) {
		ILabel b = new ILabel(text, locx, locy, callback, startX, startY, this, textBoldness);
		buttons.add(b);
		return b;
	}
	public ISprite addSprite(int id, int locx, int locy, Callback callback) {
		ISprite b = new ISprite(id, locx, locy, callback, startX, startY, this);
		buttons.add(b);
		return b;
	}
	public IPanel addPanel(int locx, int locy, int height, int width) {
		IPanel b = new IPanel(locx, locy, height, width, startX, startY, this);
		buttons.add(b);
		return b;
	}	
	public ILine addLine(int locx, int locy, int width, boolean horizontal) {
		ILine l = new ILine(locx, locy, width, horizontal, startX, startY, this);
		buttons.add(l);
		return l;
	}
	public void drawOnScreen() {
		if(!isVisible) 
			return;

		int titlebar = 30;
		mc.gameGraphics.drawBox(startX, startY-titlebar, width, titlebar, backgroundColor);
		
		mc.gameGraphics.drawLineX(startX, startY-titlebar, width, borderColor);
		mc.gameGraphics.drawLineY(startX, startY-titlebar, titlebar, borderColor);
		mc.gameGraphics.drawLineY(startX+width-1, startY-titlebar, titlebar, borderColor);
		
		mc.gameGraphics.drawLineX(startX-1, startY-titlebar-1, width+1, borderColor);
		mc.gameGraphics.drawLineY(startX-1, startY-titlebar-1, titlebar, borderColor);
		mc.gameGraphics.drawLineY(startX+width, startY-titlebar-1, titlebar, borderColor);
		
		mc.gameGraphics.drawBox(startX, startY, width, height, backgroundColor);
		mc.gameGraphics.drawBoxEdge(startX, startY, width, height, borderColor);
		mc.gameGraphics.drawBoxEdge(startX-1, startY-1, width+2, height+2, borderColor);
		
		
		
		for(RSGuiModels b : buttons) {
			if(b instanceof IPanel)
				b.drawMe(mc.gameGraphics);
		}
		for(RSGuiModels b : buttons) {
			if(!(b instanceof IPanel))
				b.drawMe(mc.gameGraphics);
		}
	}
	public void handleMouseMove(int mouseX, int mouseY) {
		if(!isVisible) 
			return ;
		for(RSGuiModels b : buttons) {
			b.handleMouseMove(mouseX, mouseY);
		}
	}
	public boolean handleClick(int mouseClick, int mouseX, int mouseY) {
		if(!isVisible) 
			return false;
		
		for(RSGuiModels b : buttons) {
			if(b.checkClick(mouseX, mouseY))
				return true;
		}
		return false;
	}
	public int textWidth(String s, int i) {
		int j = 0;
		byte abyte0[] = GameImage.aByteArrayArray336[i];
		for (int k = 0; k < s.length(); k++) {
			if (s.charAt(k) == '@' && k + 4 < s.length()
					&& s.charAt(k + 4) == '@') {
				k += 4;
			} else if (s.charAt(k) == '~' && k + 4 < s.length()
					&& s.charAt(k + 4) == '~') {
				k += 4;
			}
			else if (s.charAt(k) == '~' && k + 5 < s.length()
					&& s.charAt(k + 5) == '~') {
				k += 5;
			}else {
				try {
					j += abyte0[GameImage.charIndexes[s.charAt(k)] + 7];
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
}
