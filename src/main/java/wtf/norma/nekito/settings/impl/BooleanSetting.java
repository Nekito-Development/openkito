package wtf.norma.nekito.settings.impl;


import wtf.norma.nekito.settings.Setting;

public class BooleanSetting extends Setting {
	public boolean value;

	public BooleanSetting(String name, boolean value) {
		this.name = name;
		this.value = value;
	}

	public void setEnabled(boolean value) {
		this.value = value;
	}

	public boolean isEnabled() {
		return value;
	}
}
