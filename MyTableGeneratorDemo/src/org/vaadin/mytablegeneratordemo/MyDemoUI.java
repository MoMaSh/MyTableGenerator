package org.vaadin.mytablegeneratordemo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.myTableGenerator.MyUI;
import org.vaadin.myTableGenerator.table.autogenerate.GenerateTableInPanel;
import org.vaadin.mytablegeneratordemo.entities.Country;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("mytablegeneratordemo")
public class MyDemoUI extends MyUI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyDemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		Page.getCurrent().setTitle("MyTableGeneratorDemo");
		
		VerticalLayout vl = new VerticalLayout();
		
		GenerateTableInPanel p = new GenerateTableInPanel(Country.class);
		p.setWidth("90%");
		vl.addComponent(p);
		
		vl.setComponentAlignment(p, Alignment.MIDDLE_CENTER);
		
		setSizeUndefined();
		setContent(vl);
	}

}