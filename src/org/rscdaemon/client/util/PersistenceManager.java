package org.rscdaemon.client.util;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class PersistenceManager {
	private static final XStream xstream = new XStream();

	static {
		addAlias("NPCDef", "defs.NPCDef");
		addAlias("ItemDef", "defs.ItemDef");
		addAlias("TextureDef", "defs.extras.TextureDef");
		addAlias("AnimationDef", "defs.extras.AnimationDef");
		addAlias("ItemDropDef", "defs.extras.ItemDropDef");
		addAlias("SpellDef", "defs.SpellDef");
		addAlias("PrayerDef", "defs.PrayerDef");
		addAlias("TileDef", "defs.TileDef");
		addAlias("DoorDef", "defs.DoorDef");
		addAlias("ElevationDef", "defs.ElevationDef");
		addAlias("GameObjectDef", "defs.GameObjectDef");
		addAlias("org.rscangel.spriteeditor.Sprite",
				"org.rscdaemon.client.model.Sprite");
	}

	private static void addAlias(String name, String className) {
		try {
			xstream.alias(name, Class.forName(className));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Object load(File file) {
		try {
			InputStream is = new GZIPInputStream(new FileInputStream(file));
			Object rv = xstream.fromXML(is);
			return rv;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Failed to load cache.");
			System.exit(1);
		}
		return null;
	}

	public static void write(File file, Object o) {
		try {
			OutputStream os = new GZIPOutputStream(new FileOutputStream(file));
			xstream.toXML(o, os);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
}
