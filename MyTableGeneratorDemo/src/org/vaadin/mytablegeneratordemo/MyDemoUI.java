package org.vaadin.mytablegeneratordemo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.mytablegenerator.MyUI;
import org.vaadin.mytablegenerator.table.autogenerate.GenerateTableInPanel;
import org.vaadin.mytablegeneratordemo.entities.Country;
import org.vaadin.mytablegeneratordemo.entities.State;

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
		

		GenerateTableInPanel countryPanel = new GenerateTableInPanel(Country.class);
		countryPanel.setWidth("90%");
		vl.addComponent(countryPanel);
		vl.setComponentAlignment(countryPanel, Alignment.MIDDLE_CENTER);
		
		GenerateTableInPanel statePanel = new GenerateTableInPanel(State.class);
		statePanel.setWidth("90%");
		vl.addComponent(statePanel);
		vl.setComponentAlignment(statePanel, Alignment.MIDDLE_CENTER);

		
		
		setSizeUndefined();
		setContent(vl);
	}

}