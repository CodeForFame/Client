package org.moparscape.client;

import java.awt.DisplayMode;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JInternalFrame;

import org.moparscape.client.model.Resolutions;

public class GameFrame extends Frame
{

	public boolean translate = true;
	
	public static mudclient client = null;
	public static void setClient(mudclient c) { client = c;} 

	public void addContainer(JInternalFrame frame) {
		this.add(frame);
	}
	@SuppressWarnings("deprecation")
	public GameFrame(GameWindow gameWindow, int width, int height, String title, boolean resizable, boolean flag1) 
	{
		super(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
	
		addListeners(this, gameWindow);
    	this.setFocusable(true);
    	System.out.println("Init method for gameframe");
		requestFocus();
		frameOffset = 28;
		frameWidth = width;
		frameHeight = height;
		aGameWindow = gameWindow;
		
		setBackground(java.awt.Color.black);
		setFocusTraversalKeysEnabled(false);
		setTitle(title);
		setResizable(resizable);
		this.addWindowListener(new WindowListener(){


			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		show();
		toFront();
		resize(frameWidth, frameHeight);
		aGraphics49 = getGraphics();
		this.setFocusable(true);
		requestFocus();
		
	}
	public void addListeners(GameFrame a, GameWindow aGameWindow) {
		Inputs input = new Inputs(aGameWindow, translate);
		a.addMouseListener(input);
		a.addMouseMotionListener(input);
		a.addKeyListener(input);
	}
	
	public Graphics getGraphics() {
		Graphics g = super.getGraphics();
		if (graphicsTranslate == 0)
			g.translate(0, translate ? 24 : 0);
		else
			g.translate(-5, 0);
		return g;
	}

	@SuppressWarnings("deprecation")
	public void resize(int i, int j) {
		super.resize(i, j + frameOffset);
	}

	@SuppressWarnings("deprecation")
	public boolean handleEvent(Event event) {
		if (event.id == 401)
			aGameWindow.keyDown(event, event.key);
		else if (event.id == 402)
			aGameWindow.keyUp(event, event.key);
		else if (event.id == 501)
			aGameWindow.mouseDown(event, event.x, event.y - (translate ? 24 : 0)); //
		else if (event.id == 506)
			aGameWindow.mouseDrag(event, event.x, event.y - (translate ? 24 : 0));
		else if (event.id == 502)
			aGameWindow.mouseUp(event, event.x, event.y - (translate ? 24 : 0));
		else if (event.id == 503)
			aGameWindow.mouseMove(event, event.x, event.y - (translate ? 24 : 0));
		else if (event.id == 201)
			aGameWindow.destroy();
		else if (event.id == 1001)
			aGameWindow.action(event, event.target);
		else if (event.id == 403)
			aGameWindow.keyDown(event, event.key);
		else if (event.id == 404)
			aGameWindow.keyUp(event, event.key);
		return true;
	}

	public final void paint(Graphics g) {
		aGameWindow.paint(g);
	}
	
	public void makeFullScreen() 
    {
		System.out.println(client.windowWidth);
		System.out.println(client.windowHeight);
		if(client.windowWidth == 512 && client.windowHeight == 334) {
			mudclient.drawPopup("Full screen isn't supported with this client size. Please change your client size and try again!");
			return;
		}
		Resolutions.fs = true;
        int dwidth = client.windowWidth;
        int dheight = client.windowHeight+40;
        int bitDepth = client.screenDepth;
        int refreshRate = client.screenRefreshRate;
        
        DisplayMode dm =  new DisplayMode(dwidth, dheight, bitDepth, refreshRate);
        
    	try {
    		Resolutions.device.setFullScreenWindow(this);
    		Resolutions.device.setDisplayMode(dm);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		mudclient.drawPopup("Full screen isn't supported with this client size. Please change your client size and try again!");
    		makeRegularScreen();
    	}
    }
	public void makeRegularScreen() {
		Resolutions.device.setDisplayMode(Resolutions.oldDisplayMode);
		Resolutions.device.setFullScreenWindow(null);
		this.resize(client.windowWidth,client.windowHeight-30+40);
		Resolutions.fs = false;
	}
	int frameWidth;
	int frameHeight;
	int graphicsTranslate;
	int frameOffset;
	public GameWindow aGameWindow;
	Graphics aGraphics49;

}
