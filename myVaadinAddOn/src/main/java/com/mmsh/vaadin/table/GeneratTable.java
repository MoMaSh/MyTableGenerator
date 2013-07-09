package com.mmsh.vaadin.table;

import com.vaadin.ui.Button.ClickListener;

public class GeneratTable extends MyTable {

	private static final long serialVersionUID = 1724231700657163458L;

	public GeneratTable(Class<?> clazz) {
		super(new GeneratedTableInfo(clazz, true));
	}

	@Override
	public ClickListener saveButtonListener() {
		return null;
	}

	@Override
	public String export2excel() {
		return null;
	}

}
