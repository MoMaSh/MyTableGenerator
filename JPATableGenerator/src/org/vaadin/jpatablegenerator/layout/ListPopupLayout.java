package org.vaadin.jpatablegenerator.layout;

import java.util.List;

import org.vaadin.jpatablegenerator.table.MyTable;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Description: Creates a list pop-up layout.<br>
 * <br>
 * Filename: ListPopupLayout.java <br>
 * 
 * @since 17.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 151 $ <br>
 *          $LastChangedDate: 2013-06-20 12:39:55 +0200 (Do, 20 Jun 2013) $
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
public class ListPopupLayout extends CustomComponent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2209669874030795293L;

	/** The main content. */
	private VerticalLayout vlMainContent;
	
	/** The {@link MyTable}. */
	private MyTable osbTable;
	
	/**
	 * Instantiates a new ListPopupLayout object.
	 * @param table The {@link MyTable} which is supposed to be shown.
	 * @param selectedIds If there are any Ids selected and is supposed to
	 * be preselected.
	 */
	public ListPopupLayout(final MyTable table, final List<Integer> selectedIds) {
		setWidth("100.0%");
		setHeight("100.0%");
		
		vlMainContent = new VerticalLayout();
		vlMainContent.setImmediate(false);
		vlMainContent.setWidth("100.0%");
		vlMainContent.setHeight("100.0%");
		vlMainContent.setMargin(true);
		setCompositionRoot(vlMainContent);
		
		osbTable = table;
		osbTable.setImmediate(false);
		osbTable.setWidth("100.0%");
		osbTable.setHeight("100.0%");
		vlMainContent.addComponent(osbTable);
		vlMainContent.setExpandRatio(osbTable, 1.0f);
		vlMainContent.setComponentAlignment(osbTable, new Alignment(48));

		if (selectedIds != null) {
			for (Object obj : selectedIds) {
				osbTable.getTable().select(obj);
			}
		}
		
	}

}
