package org.mmsh.vaadin.components;

import org.mmsh.vaadin.MyUI;

import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * Description: The specialization for the the {@link MyButton} which logs out the user.<br>
 *<br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: LogoutButton.java <br>
 * 
 * @since 17.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision$ <br>
 *          $LastChangedDate$
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author$</a><br>
 */
public abstract class LogoutButton extends MyButton {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8616061697550604698L;

	/**
	 * Instantiates a new LogoutButton object. When clicked the user is set to null 
	 * and all popped up windows will be closed.
	 */
	public LogoutButton(final String userClassPath) {
		super(new ThemeResource("myVaadin/buttons/logout.png"));
		this.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -6024482019178835620L;
			@Override
			public void buttonClick(final ClickEvent event) {
				((MyUI) UI.getCurrent()).removeActivePopupWindows();
				try {
					VaadinSession.getCurrent().setAttribute(Class.forName(userClassPath), null);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				MyUI.getCurrent().setContent(setContent());
			}
		});
	}
	
	/**
	 * Sets the content of the View to the intended View.
	 * 
	 * @return The component that is supposed to fill the View.
	 */
	public abstract Component setContent();
}