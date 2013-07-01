package com.mmsh.vaadin.windows;

import com.mmsh.vaadin.table.TableInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

/**
 * Description: The Edit pop-up window.<br>
 * <br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: EditPopupWindow.java <br>
 * 
 * @since 18.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 150 $ <br>
 *          $LastChangedDate: 2013-06-19 19:34:16 +0200 (Mi, 19 Jun 2013) $
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author: mohammad.shahabi $</a><br>
 */
public class EditPopupWindow extends Window {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -328730814145776633L;

	/** The Constant MIN_HEIGHT. */
	private static final int MIN_HEIGHT = 60;

	/** The Constant MIN_WIDTH. */
	private static final int MIN_WIDTH = 20;

	/**
	 * Instantiates a new EditPopupWindow object.
	 * 
	 * @param tableInfo
	 *            The table info
	 */
	public EditPopupWindow(final TableInfo tableInfo) {
		beforeSettingContent(tableInfo);
		setContent(tableInfo.getNewEntity());
		afterSettingContent(tableInfo);
	}

	/**
	 * Instantiates a new EditPopupWindow object.
	 * 
	 * @param tableInfo
	 *            The table info
	 * @param content
	 *            The content
	 */
	public EditPopupWindow(final TableInfo tableInfo, final Component content) {
		beforeSettingContent(tableInfo);
		setContent(content);
		afterSettingContent(tableInfo);
	}

	/**
	 * General settings.
	 * 
	 * @param tableInfo
	 *            The table info
	 */
	private void beforeSettingContent(final TableInfo tableInfo) {
		center();
		setCaption(tableInfo.getPopupCaption());
		setResizable(tableInfo.isPopupResizable());
		setWidth(tableInfo.getPopupWidth() + MIN_WIDTH, Unit.PIXELS);
		setHeight(tableInfo.getPopupHeight() + MIN_HEIGHT, Unit.PIXELS);
	}

	/**
	 * After setting content.
	 * 
	 * @param tableInfo
	 *            The table info
	 */
	private void afterSettingContent(final TableInfo tableInfo) {
		getContent().setWidth(tableInfo.getPopupWidth(), Unit.PIXELS);
		getContent().setHeight(tableInfo.getPopupHeight(), Unit.PIXELS);
	}

}
