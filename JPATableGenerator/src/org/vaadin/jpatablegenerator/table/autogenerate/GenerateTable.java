package org.vaadin.jpatablegenerator.table.autogenerate;

import org.vaadin.jpatablegenerator.table.MyTable;

import com.vaadin.ui.Button.ClickListener;

public class GenerateTable extends MyTable {

	private static final long serialVersionUID = 1724231700657163458L;


	public GenerateTable(Class<?> clazz) {
		this(clazz, false);
	}

	public GenerateTable(Class<?> clazz, boolean includeId) {
		super(new GenerateTableInfo(clazz, true, includeId));
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
