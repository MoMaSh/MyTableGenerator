package org.vaadin.jpatablegenerator;

import javax.servlet.annotation.WebServlet;

import org.vaadin.jpatablegenerator.table.autogenerate.GenerateTableInPanel;
import org.vaadin.test.entities.Country;
import org.vaadin.test.entities.State;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("jpatablegenerator")
public class JpatablegeneratorUI extends MyUI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = JpatablegeneratorUI.class)
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