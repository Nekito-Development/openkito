package wtf.norma.nekito.ui.draggables.impl;

import java.util.ArrayList;

public class HudManager {

	public ArrayList<HudMod> hudMods = new ArrayList<>();

	public HudManager() {

	}

	public void renderMods() {
		for (HudMod m : hudMods) {
			m.draw();
		}
	}

}
