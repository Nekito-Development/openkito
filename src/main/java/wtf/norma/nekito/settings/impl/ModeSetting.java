package wtf.norma.nekito.settings.impl;

import wtf.norma.nekito.settings.Setting;

import java.util.ArrayList;
import java.util.Collections;

public class ModeSetting extends Setting {
	public ArrayList<String> modes = new ArrayList<>();
	public int index;

	public ModeSetting(String name, String value, String... modes) {
		this.name = name;
        Collections.addAll(this.modes, modes);
		index = this.modes.indexOf(value);
	}

	public boolean is(String name) {
		if (index >= modes.size()) {
			index = 0;
		}
		return modes.get(index).equalsIgnoreCase(name);
	}

	public String getMode() {
		if (index >= modes.size()) {
			index = 0;
		}
		return modes.get(index);
	}
	public void setMode(String selected) {
		this.index = this.modes.indexOf(selected);
	}
	public void cycle() {
		if (index < modes.size() - 1) {
			index++;
		} else {
			index = 0;
		}
	}
}
