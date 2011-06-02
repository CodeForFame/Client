package org.moparscape.client.interfaces.models;

import org.moparscape.client.GameImageMiddleMan;

public abstract class RSGuiModels {
	public abstract void drawMe(GameImageMiddleMan g);
	public abstract void handleMouseMove(int mouseX, int mouseY);
	public abstract boolean checkClick(int mouseX, int mouseY);
}
