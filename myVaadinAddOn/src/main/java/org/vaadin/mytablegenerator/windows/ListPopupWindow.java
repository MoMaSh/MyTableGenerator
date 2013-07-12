package org.vaadin.mytablegenerator.windows;

import org.vaadin.mytablegenerator.layout.ListPopupLayout;
import org.vaadin.mytablegenerator.table.MyTable;
import org.vaadin.mytablegenerator.table.TableInfo;

import com.vaadin.ui.Window;

/**
 * Description: The List pop-up window.<br>
 * <br>
 * Filename: ListPopupWindow.java <br>
 * 
 * @since 18.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 151 $ <br>
 *          $LastChangedDate: 2013-06-20 12:39:55 +0200 (Do, 20 Jun 2013) $
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
public class ListPopupWindow extends Window {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -328730814145776633L;

	/**
	 * Instantiates a new ListPopupWindow object.
	 * @param osbTable
	 *            The osb table
	 */
	public ListPopupWindow(final MyTable osbTable) {
		generalSettings(osbTable.getTableInfo());
		
		setContent(new ListPopupLayout(osbTable, null));
	}
	
	/**
	 * General settings.
	 * @param tableInfo
	 *            The table info
	 */
	private void generalSettings(final TableInfo tableInfo) {
		center();
		setCaption(tableInfo.getPopupEditCaption());
		setWidth(tableInfo.getPopupEditWidth(), Unit.PIXELS);
		setHeight(tableInfo.getPopupEditHeight(), Unit.PIXELS);
	}
}
