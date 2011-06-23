package org.rscdaemon.client.gui;

import org.rscdaemon.client.mudclient;
import org.rscdaemon.client.entityhandling.EntityHandler;
import org.rscdaemon.client.interfaces.InterfaceGUI;
import org.rscdaemon.client.interfaces.InterfaceHandler;
import org.rscdaemon.client.interfaces.models.Callback;
import org.rscdaemon.client.interfaces.models.OnMisClickCallBack;
import org.rscdaemon.client.interfaces.models.RSGuiModels;

public class OneonOne {
	public static InterfaceGUI createScreen(final mudclient mc) {
		OnMisClickCallBack onMisClick = new OnMisClickCallBack() {

			@Override
			public void run() {
				//mc.sendSmithingClose();
			}
			
		};
		Callback onclose = new Callback() {

			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				//mc.sendSmithingClose();
				gui.isVisible = false;
			}
			
		};
		/**
		 * Greates a new interface with a 450x250 panel
		 */
		InterfaceGUI i = new InterfaceGUI(470,290,mc);
		i.titlebar.label = "One on one wilderess menu!";
		i.titlebar.x = 60;
		i.xbutton.callback = onclose;
		i.outsideclick = onMisClick;
		/**
		 * Adds the interface to a list
		 */
		InterfaceHandler.guis.add(i);
		
		int y = 10;
		int x = 230;
		for(int temp = 0; temp < 30; temp++) {
			if(temp % 5 == 0 && temp != 0) {
				y += 41;
				x = 230;
			}
			i.addSprite(EntityHandler.getItemDef(0).getSprite(),x, y, null);
			x+=41;
		}
		i.addLine(220, 10, 270, false);
		
		i.addLine(10, 145, 210, true);
		i.addLabel("Your stake:", 10, 10, null, 1);
		i.addLabel("Opponents stake:", 10, 150, null, 1);
		
		i.addLine(230, 250, 230, true);
		i.addLabel("Accept", 320, 260, null, 1);//.borderColor = Color.black.hashCode();
		i.addPanel(234, 258, 220, 20).borderColor = 0x6b5e53;
		i.isVisible = true;
		return i;
	}
}
