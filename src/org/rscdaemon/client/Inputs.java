package org.rscdaemon.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.rscdaemon.client.interfaces.InterfaceHandler;

public class Inputs implements MouseListener, MouseMotionListener, KeyListener {
	public static KeyListener kl = null;
	private boolean translate = true;
	private GameWindow aGameWindow = null;
	public Inputs(GameWindow gw, boolean trans) {
		this.aGameWindow = gw;
		this.translate = trans;
	}
	public void keyPressed(KeyEvent e)
	{
		
		aGameWindow.keyDown(e.isControlDown(), e.getKeyCode(), e.getKeyChar());
		if(kl != null) {
			kl.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		aGameWindow.keyUp(e.isControlDown(), e.getKeyCode(), e.getKeyChar());
	}
	
	public void keyTyped(KeyEvent e)
	{
	}
	
	public void mouseMoved(MouseEvent e)
	{
		aGameWindow.mouseMove(e.getX(), e.getY() - (translate ? 24 : 0));
		
	}
	
	public void mouseDragged(MouseEvent e)
	{
		aGameWindow.mouseDrag(e.isMetaDown(), e.getX(), e.getY() - (translate ? 24 : 0));
	}
	
	public void mouseExited(MouseEvent e)
	{
		mouseMoved(e);
	}
	
	public void mouseEntered(MouseEvent e)
	{
		mouseMoved(e);
				
	}
	
	public void mousePressed(MouseEvent e)
	{
		
		aGameWindow.mouseDown(e.isMetaDown(), e.getX(), e.getY() - (translate ? 24 : 0));
	}
	
	public void mouseReleased(MouseEvent e)
	{
		aGameWindow.mouseUp(e.getX(), e.getY() - (translate ? 24 : 0));
	}
	
	public void mouseClicked(MouseEvent e)
	{
		InterfaceHandler.mouseClicked(0);
	}
}
