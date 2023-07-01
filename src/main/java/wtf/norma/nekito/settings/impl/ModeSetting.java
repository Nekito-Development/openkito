package wtf.norma.nekito.settings.impl;

import wtf.norma.nekito.settings.Setting;

import java.util.ArrayList;

public class ModeSetting extends Setting {
	public ArrayList<String> modes = new ArrayList<>();
	public int index;

	public ModeSetting(String name, String value, String... modes) {
		this.name = name;
		for (String s : modes) {
			this.modes.add(s);
		}
		index = this.modes.indexOf(value);
	}

	public boolean is(String name) {
		if (index >= modes.size()) {
			index = 0;
		}
		return modes.get(index).toLowerCase().equals(name.toLowerCase());
	}

	public String getMode() {
		if (index >= modes.size()) {
			index = 0;
		}
		return modes.get(index);
	}
	
	public void cycle() {
		if (index < modes.size() - 1) {
			index++;
		} else {
			index = 0;
		}
	}
}
