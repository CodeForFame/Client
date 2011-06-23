import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import org.moparscape.iface.ClientInterface;
import org.moparscape.userver.Server;
import org.rscdaemon.client.Inputs;
import org.rscdaemon.client.mudclient;
import org.rscdaemon.client.util.Config;

public class client extends mudclient implements ClientInterface {

	@Override
	public void setServer(String server) {
		Config.SERVER_IP = server;
	}

	@Override
	public void setPort(int port) {
		Config.SERVER_PORT = port;
	}

	@Override
	public int getPort() {
		return Config.SERVER_PORT;
	}

	@Override
	public void setCacheDir(String cacheDir) {
		Config.CONF_DIR = cacheDir;
		Config.MEDIA_DIR = cacheDir;
	}

	@Override
	public void setKeyListener(KeyListener kl) {
		Inputs.kl = kl;
	}
	
	@Override
	public Map<String, String> getParams() {
		// There are none...
		return new HashMap<String, String>();
	}
	
	// Not required.

	@Override
	public void setOndemandPort(int port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJaggrabPort(int port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMapLock(boolean maplock, int mapface) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHPheads(boolean on) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setZoom(boolean on) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modZoomInts(int zoom, int fwdbwd, int lftrit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Server[] getUpdateServers(String defaultLocation,
			String customLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(windowWidth, windowHeight);
	}

	@Override
	public void setBackground(Image image) {
		// TODO Auto-generated method stub
		
	}

}