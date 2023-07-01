package wtf.norma.nekito.settings.impl;

import wtf.norma.nekito.settings.Setting;

public class KeybindSetting extends Setting {
	public int key;

	public KeybindSetting(String name, int value) {
		this.name = name;
		this.key = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
