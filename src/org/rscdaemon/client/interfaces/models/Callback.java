package org.rscdaemon.client.interfaces.models;

import org.rscdaemon.client.interfaces.InterfaceGUI;

public interface Callback{
	void run(InterfaceGUI gui, RSGuiModels id);
}

