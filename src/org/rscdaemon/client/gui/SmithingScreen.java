package org.rscdaemon.client.gui;

import org.rscdaemon.client.mudclient;
import org.rscdaemon.client.entityhandling.EntityHandler;
import org.rscdaemon.client.interfaces.InterfaceGUI;
import org.rscdaemon.client.interfaces.InterfaceHandler;
import org.rscdaemon.client.interfaces.models.Callback;
import org.rscdaemon.client.interfaces.models.HoverCallback;
import org.rscdaemon.client.interfaces.models.ILabel;
import org.rscdaemon.client.interfaces.models.IPanel;
import org.rscdaemon.client.interfaces.models.ISprite;
import org.rscdaemon.client.interfaces.models.OnMisClickCallBack;
import org.rscdaemon.client.interfaces.models.RSGuiModels;
import org.rscdaemon.client.util.Util;

public class SmithingScreen {
	
	public static void changeItems(InterfaceGUI i, int id) {
		int pointer = 0;
		int[] items;
		switch(id) {
			case 0:
				items = Util.BRONZE;
				break;
			case 1:
				items = Util.IRON;
				break;	
			case 2:
				items = Util.STEEL;
				break;
			case 3:
				items = Util.MITH;
				break;	
			case 4:
				items = Util.ADDY;
				break;	
			case 5:
				items = Util.RUNE;
				break;
			default:
				items = Util.IRON;
				break;
		}
		for(RSGuiModels c : i.buttons) {
			if(c instanceof ISprite) {
				ISprite sprite = (ISprite)c;
				sprite.spriteID = items[pointer++];
			}
		}
	}
	
	public static InterfaceGUI createScreen(final mudclient mc) {
		OnMisClickCallBack onMisClick = new OnMisClickCallBack() {

			@Override
			public void run() {
				mc.sendSmithingClose();
				
			}
			
		};
		Callback onclose = new Callback() {

			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				mc.sendSmithingClose();
				
			}
			
		};
		/**
		 * Greates a new interface with a 450x250 panel
		 */
		InterfaceGUI i = new InterfaceGUI(450,250,mc);
		i.titlebar.label = "What would you like to do?";
		i.titlebar.x = 100;
		i.xbutton.callback = onclose;
		i.outsideclick = onMisClick;
		/**
		 * Adds the interface to a list
		 */
		InterfaceHandler.guis.add(i);
		/**
		 * Called when the label is clicked
		 */
		Callback labelcallback = new Callback(){
			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				
			}
		};
		/**
		 * Creates a label (text) and adds it to the interface at specific coordinates (the label can be altered at runtime)
		 * Supports: Hover, click
		 */
		final ILabel l = i.addLabel("",220, 215, labelcallback, 1);
		l.label = "wot nao?";
		/**
		 * Called when the sprite is clicked
		 */
		Callback spritecallback = new Callback(){
			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				ISprite sprite = (ISprite)id;
				mc.sendSmithingItem(sprite.spriteID);
				}	
		};
		HoverCallback spritehover = new HoverCallback() {

			@Override
			public void hoverin(RSGuiModels c) {
				ISprite sprite = ((ISprite)c);
				l.label = getBarLine(sprite.spriteID);
				
			}
			@Override
			public void hoverout(RSGuiModels c) {
				l.label = "";
			}
			
		};
		HoverCallback panelhover = new HoverCallback() {

			@Override
			public void hoverin(RSGuiModels c) {
				if(c instanceof IPanel) {
					((IPanel)c).borderColor = 0x6b5e53;
				}
				
			}
			@Override
			public void hoverout(RSGuiModels c) {
				if(c instanceof IPanel) {
					((IPanel)c).borderColor = 0x463d2e;
				}
				
			}
			
		};
		int pointer = 0;
		/*
		int[] items = Util.IRON;
		int y = 10;
		int x = 30;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y+=50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y+=50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		
		y+=50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	

		
		y = 10;
		x = 170+30;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y += 50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y += 50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y += 50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y = 10;
		x += 120;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		
		y += 50;
		i.addSprite(items[pointer++],x, y, spritecallback).hoverCallback = spritehover;
		i.addSprite(items[pointer++],x+50, y, spritecallback).hoverCallback = spritehover;
		i.addPanel(x, y-5, 50, 40).hoverCallback = panelhover;	
		i.addPanel(x+51, y-5, 50, 40).hoverCallback = panelhover;
		*/
		
		i.addLine(170, 20, 170, false);
		
		i.addLine(10, 200, 430, true);
			
		return i;
	}
	public static String getBarLine(int id) {
		String name = EntityHandler.getItemDef(id).name.toLowerCase();
		if(name.contains("large") && name.contains("helmet")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 2 bars to smith";
		}
		if(name.contains("medium") && name.contains("helmet")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("chain") && name.contains("mail")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 3 bars to smith";
		}
		if(name.contains("plate") && name.contains("body")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 5 bars to smith";
		}
		if(name.contains("plate") && name.contains("leg")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 3 bars to smith";
		}
		if(name.contains("kite") && name.contains("shield")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 3 bars to smith";
		}
		if(name.contains("square") && name.contains("shield")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 2 bars to smith";
		}
		if(name.contains("short") && name.contains("sword")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("long") && name.contains("sword")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 2 bars to smith";
		}
		if(name.contains("scimitar")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 2 bars to smith";
		}	
		if(name.contains("battle") && name.contains("axe")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 3 bars to smith";
		}
		if(name.contains("pickaxe")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("axe") && !name.contains("battle")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("dagger")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("mace")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("arrow") && name.contains("heads")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}
		if(name.contains("dart") && name.contains("tips")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}	
		if(name.contains("throwing") && name.contains("knife")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 1 bar to smith";
		}	
		if(name.contains("2-handed") && name.contains("sword")) {
			return "This item ("+EntityHandler.getItemDef(id).name+") requires 3 bar to smith";
		}	
		return name;
	}
}
