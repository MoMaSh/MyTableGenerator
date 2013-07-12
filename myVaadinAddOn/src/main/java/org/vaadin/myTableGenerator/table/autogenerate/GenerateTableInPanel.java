package org.vaadin.myTableGenerator.table.autogenerate;

import com.vaadin.ui.Panel;

public class GenerateTableInPanel extends Panel {

	private static final long serialVersionUID = 4661895296440961807L;

	public GenerateTableInPanel(Class<?> clazz) {
		GenerateTable gt = new GenerateTable(clazz);
		this.setContent(gt);
		this.setCaption(gt.getTableInfo().getCaption());
	}

}