package org.rscdaemon.client.cache;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.rscdaemon.client.util.Config;
import org.rscdaemon.client.util.Downloader;

public class CacheManager {
		
	static {
		load("MD5CHECKSUM");
		
		Properties old = new Properties();
		Properties new1= new Properties();
		try {
			File f = new File(Config.CONF_DIR + File.separator + "MD5CHECKSUM.old");
			if(!f.exists()) {
				f.createNewFile();
			}
			old.load(new FileInputStream(f));
			new1.load(new FileInputStream(Config.CONF_DIR + File.separator + "MD5CHECKSUM"));
		} catch (Exception e) {
			System.out.println("Unable to load checksums.");
			System.exit(1);
		}
		
		deleteNonExistant(old.keySet(), new1.keySet());
		updateIfNeeded(old.entrySet(), new1.entrySet());
		downloadNew(old.keySet(), new1.keySet());
	}
	
	public static File load(String filename) {
		File f = new File(Config.CONF_DIR + File.separator + filename);
		System.out.printf("Checking for %s.%n", f.getAbsolutePath());
		if (!f.exists()) {
			try {
				Downloader.download(f);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.printf("Failed to load %s.", f.getName());
				System.exit(1);
			}
			return load(filename);
		} else
			return f;
	}

	private static void downloadNew(Set<Object> keySet, Set<Object> keySet2) {
		for(Object o : keySet2) {
			if(!keySet.contains(o)) {
				update((String) o);
			}
		}
	}

	private static void updateIfNeeded(Set<Entry<Object, Object>> entrySet,
			Set<Entry<Object, Object>> entrySet2) {
		for(Entry<Object, Object> e : entrySet) {
			Iterator<Entry<Object, Object>> itr = entrySet2.iterator();
			while(itr.hasNext()) {
				Entry<Object, Object> e1 = itr.next();
				if(e1.getKey().equals(e.getKey()) && !e1.getValue().equals(e.getValue())) {
					update((String) e.getKey());
				}
			}
		}
	}

	private static void deleteNonExistant(Set<Object> keySet,
			Set<Object> keySet2) {
		for(Object o : keySet) {
			if(!keySet2.contains(o)) {
				new File(Config.CONF_DIR + File.separator + (String) o).delete();
			}
		}
	}

	private static void update(String filename) {
		new File(Config.CONF_DIR + File.separator + filename).delete();
		load(filename);
	}

	public static void doneLoading() {
		File old = new File(Config.CONF_DIR + File.separator + "MD5CHECKSUM.old");
		File new1 = new File(Config.CONF_DIR + File.separator + "MD5CHECKSUM");

		old.delete();
		new1.renameTo(old);
	}
}
