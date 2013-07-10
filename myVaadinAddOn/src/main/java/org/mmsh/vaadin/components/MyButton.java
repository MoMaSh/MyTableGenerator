package org.mmsh.vaadin.components;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

/**
 * Description: The generalization for Vaadin {@link Button}.<br>
 * <br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: MyButton.java <br>
 * 
 * @since 13.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 150 $ <br>
 *          $LastChangedDate: 2013-06-19 19:34:16 +0200 (Mi, 19 Jun 2013) $
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author: mohammad.shahabi $</a><br>
 */
public class MyButton extends Button {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5063884137760745432L;

	/**
	 * Instantiates a new MyButton object.
	 * 
	 * @param themeResource Indicates the icon that is supposed to be shown instead. <br>
	 * If <code>null</code> the normal button will be shown. 
	 */
	public MyButton(final ThemeResource themeResource) {
		this.setImmediate(true);
		this.setStyleName("tableBtn");
		this.setWidth("-1px");
		this.setHeight("-1px");
		if (themeResource != null) {
			this.setCaption("");
			this.addStyleName("link");
			this.setIcon(themeResource);
		}
	}
	
}
