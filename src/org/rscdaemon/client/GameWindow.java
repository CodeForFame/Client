package org.rscdaemon.client;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.rscdaemon.client.interfaces.InterfaceHandler;
import org.rscdaemon.client.model.Display;
import org.rscdaemon.client.util.Config;

public class GameWindow extends Applet implements Runnable {

	public int getYOffset() {
		return 0;
	}

	private static mudclient client;

	public static void setClient(mudclient c) {
		client = c;
	}

	public static int gameWidth = 800;
	public static int gameHeight = 600 - 30;
	public static final Color BAR_COLOUR = new Color(120, 0, 0);
	public static final Font LOADING_FONT = new Font("Helvetica", 0, 12);

	protected void startGame() {
	}

	protected synchronized void method2() {
	}

	protected void logoutAndStop() {
	}

	protected synchronized void method4() {
	}

	protected final void createWindow(int width, int height, String title,
			boolean resizable) {
		appletWidth = width;
		appletHeight = height;
		gameFrame = new GameFrame(this, width, height, title, resizable, false);
		loadingScreen = 1;
		gameWindowThread = new Thread(this);
		gameWindowThread.start();
		gameWindowThread.setPriority(1);
	}

	public void setLogo(Image logo) {
		// loadingLogo = logo;
	}

	protected final void changeThreadSleepModifier(int i) {
		threadSleepModifier = 1000 / i;
	}

	protected final void resetCurrentTimeArray() {
		for (int i = 0; i < 10; i++) {
			currentTimeArray[i] = 0L;
		}
	}

	public final synchronized boolean keyDown(boolean ctrl, int key,
			char keyChar) {
		controlDown = ctrl;
		keyDown = key;
		keyDown2 = key;

		if (!controlDown)
			handleMenuKeyDown(key, keyChar);

		lastActionTimeout = 0;

		// KO9 mod
		// System.out.println(key);
		if (key == 45)
			minus = true;
		else if (key == 61)
			plus = true;
		else if (key == 103)
			home = true;
		else if (key == 105)
			pageUp = false;
		else if (key == 99)
			pageDown = false;
		else if (key == 37)
			keyLeftDown = true;
		else if (key == 39)
			keyRightDown = true;
		else if (key == 38)
			keyUpDown = true;
		else if (key == 40)
			keyDownDown = true;
		else if (key == 127)
			keyDelDown = true;
		else if (keyChar == ' ')
			keySpaceDown = true;
		else if (keyChar == 'n' || keyChar == 'm')
			keyNMDown = true;
		else if (keyChar == 'N' || keyChar == 'M')
			keyNMDown = true;
		else if (keyChar == '{')
			keyLeftBraceDown = true;
		else if (keyChar == '}')
			keyRightBraceDown = true;
		else if (key == 112) // 1008 or F1
			keyF1Toggle = !keyF1Toggle;
		else if (key == 113) // F2
		{
			Config.storeConfig("width", ""
					+ Display.displaymodes.get(0).getWidth());
			Config.storeConfig("height", ""
					+ Display.displaymodes.get(0).getHeight());
			Config.storeConfig("refreshRate", ""
					+ Display.displaymodes.get(0).getRefreshRate());
			Config.storeConfig("bitDepth", ""
					+ Display.displaymodes.get(0).getBitDepth());
			client.displayMessage(
					"@gre@Client size reset to default, please restart!", 3, 0);
		}
		boolean validKeyDown = false;
		for (int j = 0; j < charSet.length(); j++) {
			if (key != charSet.charAt(j))
				continue;

			validKeyDown = true;
			break;
		}

		if (validKeyDown && inputText.length() < 20)
			inputText += keyChar;
		if (validKeyDown && inputMessage.length() < 80)
			inputMessage += keyChar;
		if (key == 8 && inputText.length() > 0) // backspace
			inputText = inputText.substring(0, inputText.length() - 1);
		if (key == 8 && inputMessage.length() > 0) // backspace
			inputMessage = inputMessage.substring(0, inputMessage.length() - 1);
		if (key == 10 || key == 13) { // enter/return
			enteredText = inputText;
			enteredMessage = inputMessage;
		}
		return true;
	}

	protected void handleMenuKeyDown(int key, char keyChar) {
	}
	
	@Override
	public final void run() {
		if (loadingScreen == 1) {
			loadingScreen = 2;
			setLoadingGraphics(getGraphics());
			drawLoadingLogo();
			drawLoadingScreen(0, "Loading...");
			startGame();
			loadFonts();
			loadingScreen = 0;
		}
		int i = 0;
		int j = 256;
		int sleepTime = 1;
		int i1 = 0;
		for (int j1 = 0; j1 < 10; j1++)
			currentTimeArray[j1] = System.currentTimeMillis();

		while (exitTimeout >= 0) {
			if (exitTimeout > 0) {
				exitTimeout--;
				if (exitTimeout == 0) {
					close();
					gameWindowThread = null;
					return;
				}
			}
			int k1 = j;
			int i2 = sleepTime;
			j = 300;
			sleepTime = 1;
			long l1 = System.currentTimeMillis();
			if (currentTimeArray[i] == 0L) {
				j = k1;
				sleepTime = i2;
			} else if (l1 > currentTimeArray[i])
				j = (int) ((long) (2560 * threadSleepModifier) / (l1 - currentTimeArray[i]));
			if (j < 25)
				j = 25;
			if (j > 256) {
				j = 256;
				sleepTime = (int) ((long) threadSleepModifier - (l1 - currentTimeArray[i]) / 10L);
				try {
					if(sleepTime > 60) sleepTime = 60;
					Thread.sleep(sleepTime);
				} catch (InterruptedException _ex) {
					if (sleepTime < threadSleepTime)
						sleepTime = threadSleepTime;
				}
			}
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException _ex) {
				}
				currentTimeArray[i] = l1;
				i = (i + 1) % 10;
				if (sleepTime > 1) {
					for (int j2 = 0; j2 < 10; j2++)
						if (currentTimeArray[j2] != 0L)
							currentTimeArray[j2] += sleepTime;

				}
				int k2 = 0;
				while (i1 < 256) {
					method2();
					i1 += j;
					if (++k2 > anInt5) {
						i1 = 0;
						anInt10 += 6;
						if (anInt10 > 25) {
							anInt10 = 0;
							keyF1Toggle = true;
						}
						break;
					}
				}
				anInt10--;
				i1 &= 0xff;
				method4();
			}
			if (exitTimeout == -1)
				close();
			gameWindowThread = null;
		}

	public final synchronized boolean keyUp(boolean ctrl, int i, char keyChar) {
		keyDown = 0;

		controlDown = ctrl;
		// zoom

		if (keyChar == '-')
			minus = false;
		else if (keyChar == '+')
			plus = false;
		else if (i == 103)
			home = false;
		else if (i == 105)
			pageUp = false;
		else if (i == 99)
			pageDown = false;
		else if (i == 127)
			keyDelDown = false;
		else if (i == 37)
			keyLeftDown = false;
		if (i == 39)
			keyRightDown = false;
		if (i == 38)
			keyUpDown = false;
		if (i == 40)
			keyDownDown = false;
		if (keyChar == ' ')
			keySpaceDown = false;
		if (keyChar == 'n' || keyChar == 'm')
			keyNMDown = false;
		if (keyChar == 'N' || keyChar == 'M')
			keyNMDown = false;
		if (keyChar == '{')
			keyLeftBraceDown = false;
		if (keyChar == '}')
			keyRightBraceDown = false;

		return true;
	}

	public final synchronized boolean mouseMove(int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		InterfaceHandler.mouseMove(mouseX, mouseY);
		mouseDownButton = 0;
		lastActionTimeout = 0;
		return true;
	}

	public final synchronized boolean mouseUp(int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		// InterfaceHandler.mouseMove(mouseX, mouseY);

		mouseDownButton = 0;
		return true;
	}

	public final synchronized boolean mouseDown(boolean meta, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		if (InterfaceHandler.insideAnInterface(mouseX, mouseY)) {
			return false;
		}
		// InterfaceHandler.mouseMove(mouseX, mouseY);

		mouseDownButton = meta ? 2 : 1;

		lastMouseDownButton = mouseDownButton;
		lastActionTimeout = 0;
		handleMouseDown(mouseDownButton, i, j);
		return true;
	}

	protected void handleMouseDown(int button, int x, int y) {
	}

	public final synchronized boolean mouseDrag(boolean meta, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		if (InterfaceHandler.insideAnInterface(mouseX, mouseY)) {
			return false;
		}
		// InterfaceHandler.mouseMove(mouseX, mouseY);
		mouseDownButton = meta ? 2 : 1;
		// handleMouseDrag(event, i, j, mouseDownButton);
		return true;
	}

	protected void handleMouseDrag(MouseEvent mouse, int x, int y, int button) {
	}

	public final void init() {

		System.out.println("Started applet");
		appletWidth = gameWidth;
		appletHeight = gameHeight;

		loadingScreen = 1;
		startThread(this);
		addListeners(this);

	}

	public void addListeners(GameWindow aGameWindow) {
		Inputs input = new Inputs(aGameWindow, false);
		aGameWindow.addMouseListener(input);
		aGameWindow.addMouseMotionListener(input);
		aGameWindow.addKeyListener(input);
	}

	public final void start() {
		if (exitTimeout >= 0) {
			exitTimeout = 0;
		}
		try {
			mudclient.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final void stop() {
		if (exitTimeout >= 0) {
			exitTimeout = 4000 / threadSleepModifier;
		}
	}

	public final void destroy() {
		exitTimeout = -1;
		try {
			Thread.sleep(2000L);
		} catch (Exception e) {
		}
		if (exitTimeout == -1) {
			System.out.println("2 seconds expired, forcing kill");
			close();
			if (gameWindowThread != null) {
				gameWindowThread.stop();
				gameWindowThread = null;
			}
		}
	}

	private final void close() {
		exitTimeout = -2;
		System.out.println("Closing program");
		logoutAndStop();
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (gameFrame != null) {
			gameFrame.dispose();
		}
		System.exit(0);
	}

        private void loadFonts() {
                byte byte_arr[] = null;
                try {
                        byte_arr = DataFileDecrypter.load(Config.CONF_DIR + File.separator + "data" + File.separator + "jagex1.jag");
                } catch (IOException e) {
                        e.printStackTrace();
                }
                String[] font_names = {
                                "h11p.jf", "h12b.jf", "h12p.jf",
                                "h13b.jf", "h14b.jf", "h16b.jf",
                                "h20b.jf", "h24b.jf"
                };
                for(int i = 0; i < font_names.length; i++)
                        GameImage.loadFont_(DataFileDecrypter.loadCachedData(font_names[i], byte_arr));
        }

	public final void update(Graphics g) {
		paint(g);
	}

	public final void paint(Graphics g) {
		if (loadingScreen == 2 && loadingLogo != null) {
			drawLoadingScreen(anInt16, loadingBarText);
		}
	}

	final void drawLoadingLogo() {
		getLoadingGraphics().setColor(Color.black);
		getLoadingGraphics().drawImage(loadingLogo, appletWidth, appletHeight,
				5, 0, this);
	}

	final void drawLoadingScreen(int i, String s) {
		try {
			int j = (appletWidth - 281) / 2;
			int k = (appletHeight - 148) / 2;
			getLoadingGraphics().setColor(Color.black);
			getLoadingGraphics().fillRect(0, 0, appletWidth, appletHeight);
			getLoadingGraphics().drawImage(loadingLogo, 0, 0, appletWidth,
					appletHeight, this);
			j += 2;
			k += 120;
			anInt16 = i;
			loadingBarText = s;
			// getLoadingGraphics().setColor(BAR_COLOUR);
			// getLoadingGraphics().drawRect(j - 2, k - 2, 280, 23);
			getLoadingGraphics().fillRect(j, k, (277 * i) / 100, 20);
			getLoadingGraphics().setColor(Color.black);
			drawString(getLoadingGraphics(), s, LOADING_FONT, j + 138, k + 10);
			if (loadingString != null) {
				getLoadingGraphics().setColor(Color.WHITE);
				drawString(getLoadingGraphics(), loadingString, LOADING_FONT,
						j + 138, k - 120);
				return;
			}
		} catch (Exception _ex) {
		}
	}

	protected final void drawLoadingBarText(int i, String s) {
		try {
			int j = (appletWidth - 281) / 2;
			int k = (appletHeight - 148) / 2;
			j += 2;
			k += 120;
			anInt16 = i;
			loadingBarText = s;
			int l = (277 * i) / 100;
			getLoadingGraphics().setColor(BAR_COLOUR);
			getLoadingGraphics().fillRect(j, k, l, 20);
			getLoadingGraphics().setColor(Color.black);
			getLoadingGraphics().fillRect(j + l, k, 277 - l, 20);
			getLoadingGraphics().setColor(Color.WHITE);
			drawString(getLoadingGraphics(), s, LOADING_FONT, j + 138, k + 10);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	protected final void drawString(Graphics g, String s, Font font, int i,
			int j) {
		FontMetrics fontmetrics = (gameFrame == null ? this : gameFrame)
				.getFontMetrics(font);
		fontmetrics.stringWidth(s);
		g.setFont(font);
		g.drawString(s, i - fontmetrics.stringWidth(s) / 2,
				j + fontmetrics.getHeight() / 4);
	}

	protected final static void drawStringStatic(Graphics g, String s,
			Font font, int i, int j) {
		FontMetrics fontmetrics = gameFrame.getFontMetrics(font);
		fontmetrics.stringWidth(s);
		g.setFont(font);
		g.drawString(s, i, j);
	}

	protected byte[] load(String filename) {
		int j = 0;
		int k = 0;
		byte abyte0[] = null;
		try {
			java.io.InputStream inputstream = DataOperations
					.streamFromPath(filename);
			DataInputStream datainputstream = new DataInputStream(inputstream);
			byte abyte2[] = new byte[6];
			datainputstream.readFully(abyte2, 0, 6);
			j = ((abyte2[0] & 0xff) << 16) + ((abyte2[1] & 0xff) << 8)
					+ (abyte2[2] & 0xff);
			k = ((abyte2[3] & 0xff) << 16) + ((abyte2[4] & 0xff) << 8)
					+ (abyte2[5] & 0xff);
			int l = 0;
			abyte0 = new byte[k];
			while (l < k) {
				int i1 = k - l;
				if (i1 > 1000) {
					i1 = 1000;
				}
				datainputstream.readFully(abyte0, l, i1);
				l += i1;
			}
			datainputstream.close();
		} catch (IOException _ex) {
			_ex.printStackTrace();
		}
		if (k != j) {
			byte abyte1[] = new byte[j];
			DataFileDecrypter.unpackData(abyte1, j, abyte0, k, 0);
			return abyte1;
		} else {
			return abyte0;
		}
	}

	public Graphics getGraphics() {
		if (gameFrame != null) {
			return gameFrame.getGraphics();
		}
		return super.getGraphics();
	}

	public Image createImage(int i, int j) {
		if (gameFrame != null) {
			return gameFrame.createImage(i, j);
		}
		return super.createImage(i, j);
	}

	protected Socket makeSocket(String address, int port) throws IOException {
		Socket socket = new Socket(InetAddress.getByName(address), port);
		socket.setSoTimeout(30000);
		socket.setTcpNoDelay(true);
		return socket;
	}

	protected void startThread(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();
	}

	public GameWindow() {
		appletWidth = gameWidth;
		appletHeight = gameHeight + 40;
		threadSleepModifier = 20;
		anInt5 = 1000;
		currentTimeArray = new long[10];
		loadingScreen = 1;
		loadingBarText = "Loading";
		keyLeftBraceDown = false;
		keyRightBraceDown = false;
		keyLeftDown = false;
		keyRightDown = false;
		keyUpDown = false;
		keyDownDown = false;
		keySpaceDown = false;
		keyDelDown = false;
		keyNMDown = false;
		threadSleepTime = 1;
		keyF1Toggle = false;
		inputText = "";
		enteredText = "";
		inputMessage = "";
		enteredMessage = "";
	}

	public void setLoadingGraphics(Graphics loadingGraphics) {
		GameWindow.loadingGraphics = loadingGraphics;
	}

	public static Graphics getLoadingGraphics() {
		return loadingGraphics;
	}

	private Image loadingLogo;
	protected static int appletWidth;
	protected static int appletHeight;
	protected Thread gameWindowThread;
	private int threadSleepModifier;
	private int anInt5;
	private long currentTimeArray[];
	public static GameFrame gameFrame = null;
	private int exitTimeout;
	private int anInt10;
	public int yOffset;
	public int lastActionTimeout;
	public int loadingScreen;
	public String loadingString;
	private int anInt16;
	private String loadingBarText;
	private static Graphics loadingGraphics;
	private static String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
	public boolean keyLeftBraceDown;
	public boolean keyRightBraceDown;
	public boolean keyLeftDown;
	public boolean keyRightDown;
	public boolean keyUpDown;
	public boolean keyDownDown;
	public boolean keyDelDown;
	public boolean keySpaceDown;
	public boolean keyNMDown;
	public boolean controlDown;
	public boolean pageUp;
	public boolean pageDown;
	public boolean home;
	public boolean minus;
	public boolean plus;
	public int threadSleepTime;
	public int mouseX;
	public int mouseY;
	public int mouseDownButton;
	public int lastMouseDownButton;
	public int keyDown;
	public int keyDown2;
	public boolean keyF1Toggle;
	public String inputText;
	public String enteredText;
	public String inputMessage;
	public String enteredMessage;

}
