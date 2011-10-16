package org.rscdaemon.client.util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader {

	public static void download(File f) throws Exception {
		URL url = new URL(Config.CACHE_URL + f.getName());
		System.out.printf("Downloading %s from %s.%n", f.getAbsolutePath(), url.toString());
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(Config.CONF_DIR + File.separator + f.getName());
		int i = 0;
		while(fos.getChannel().transferFrom(rbc, (1L << 24) * i++, 1 << 24) > 0);
	}

}
