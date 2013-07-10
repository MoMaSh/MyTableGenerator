package org.mmsh.vaadin.components;

import org.mmsh.vaadin.MyUI;
import org.mmsh.vaadin.common.MyTableAnnot;
import org.mmsh.vaadin.table.GeneratedTableInfo;
import org.mmsh.vaadin.table.MyTable;
import org.mmsh.vaadin.table.TableInfo;

import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

/**
 * Description: Generalization for Command buttons that are supposed to view <br>
 * the corresponding tables. Here there are some reflections used.<br>
 * 
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: CommandButton.java <br>
 * 
 * @since 17.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision$ <br>
 *          $LastChangedDate$
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author$</a><br>
 */
public abstract class CommandButton extends MyButton {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5063884137760745432L;

	/**
	 * Instantiates a new MyButton object.
	 * @param themeResource
	 *            Indicates the icon that is supposed to be shown instead. <br>
	 *            If <code>null</code> the normal button will be shown.
	 * @param classForName The string value of the entity class path. 
	 * @param mainViewPanel The component which is supposed to contain the table.
	 */
	public CommandButton(final ThemeResource themeResource, final Class<?> clazz, final Panel mainViewPanel) {
		super(themeResource);
		this.addClickListener(commandListener(clazz, mainViewPanel));
	}

	/**
	 * When a command button is clicked the corresponding table is shown.
	 * 
	 * @param classForName The string value of the entity class path. 
	 * @param mainViewPanel The component which is supposed to contain the table.
	 * @return The action dedicated to the command button when it is clicked.
	 */
	private ClickListener commandListener(final Class<?> clazz, final Panel mainViewPanel) {
		return new ClickListener() {
			private static final long serialVersionUID = -5812537411555467805L;

			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(final ClickEvent event) {
				((MyUI) UI.getCurrent()).removeActivePopupWindows();
				TableInfo tableInfo = null;
		      	try {
		    		Class<TableInfo> classTableInfo = (Class<TableInfo>) Class.forName(clazz.getCanonicalName() + "TableInfo");
		    		tableInfo = classTableInfo.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					MyTableAnnot classAnnotation = clazz.getAnnotation(MyTableAnnot.class);
					if (classAnnotation != null) {
						tableInfo = new GeneratedTableInfo(clazz, true);
					} else {
						e.printStackTrace();
					}
				}
		      	assert (tableInfo != null); 
				Page.getCurrent().setTitle(tableInfo.getCaption());
				mainViewPanel.setCaption(tableInfo.getCaption());
				mainViewPanel.setContent(setContent(tableInfo));	
			}

		};
	}
	
	/**
	 * Sets the content of the mainViewPanel to the intended component.
	 * 
	 * @param tableInfo
	 *            The {@link TableInfo}.
	 * @return The component that is supposed to fill the mainViewPanel.
	 */
	public abstract MyTable setContent(TableInfo tableInfo);
}
