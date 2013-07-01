package com.mmsh.vaadin.components;

import com.mmsh.vaadin.MyUI;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

/**
 * Description: This is a specific Button component from <a>{@link MyButton}</a> which creates a <br> 
 * button that closes the popped up window.<br>
 * <br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: CancelButton.java <br>
 * 
 * @since 13.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 156 $ <br>
 *          $LastChangedDate: 2013-06-21 11:01:01 +0200 (Fr, 21 Jun 2013) $
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author: mohammad.shahabi $</a><br>
 */
public class CancelButton extends MyButton {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5114249737817111534L;

	/**
	 * Instantiates a new CancelButton object.
	 * 
	 * @param isEdit Indicates the type of active window.  
	 */
	public CancelButton(final boolean isEdit) {
		super(new ThemeResource("osbImages/buttons/notSave64.png"));
		this.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -9132805767679652865L;
			@Override
			public void buttonClick(final ClickEvent event) {
				if (isEdit) {
					((MyUI) UI.getCurrent()).removeActiveEditPopupWindow();
				} else {
					((MyUI) UI.getCurrent()).removeActiveListPopupWindow();
				}
			}
		});
	}
	
}
