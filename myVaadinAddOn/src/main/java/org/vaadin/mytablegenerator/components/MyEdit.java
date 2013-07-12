package org.vaadin.mytablegenerator.components;

import org.vaadin.mytablegenerator.table.TableInfo;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Description: The edit layout.<br>
 * <br>
 * Filename: MyEdit.java <br>
 * 
 * @since 19.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision$ <br>
 *          $LastChangedDate$
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
public abstract class MyEdit extends CustomComponent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4020534777141098215L;

	/** The txt id. */
	private TextField txtId;

	/** The main layout. */
	private VerticalLayout mainLayout;

	/** The save btn. */
	private MyButton saveBtn;

	/** The cancel btn. */
	private CancelButton cancelBtn;

	/** The actions layout. */
	private HorizontalLayout actionsLayout;

	/** The dynamic layout. */
	private Layout dynamicLayout;

	/**
	 * Instantiates a new MyEdit object.
	 * 
	 * @param tableInfo
	 *            The table info
	 */
	public MyEdit(final TableInfo tableInfo) {
		mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		txtId = new TextField();
		txtId.setEnabled(false);
		txtId.setVisible(false);
		mainLayout.addComponent(txtId);

		dynamicLayout = generateContent();
		mainLayout.addComponent(dynamicLayout);
		mainLayout.setExpandRatio(dynamicLayout, 1.0f);
		mainLayout
				.setComponentAlignment(dynamicLayout, Alignment.MIDDLE_CENTER);

		generateActionsLayout();
		saveBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1609549147129628107L;

			@Override
			public void buttonClick(final ClickEvent event) {
				try {
					saveAction(tableInfo);
					txtId.setReadOnly(true);
				} catch (Exception e) {
					e.printStackTrace();
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
				}
			}
		});
		setCompositionRoot(mainLayout);
		setSizeFull();
	}

	/**
	 * Generate actions layout.
	 */
	private void generateActionsLayout() {
		actionsLayout = new HorizontalLayout();
		actionsLayout.setMargin(new MarginInfo(false, true, true, true));
		saveBtn = new MyButton(new ThemeResource("myVaadin/buttons/save.png"));
		actionsLayout.addComponent(saveBtn);
		cancelBtn = new CancelButton(true);
		actionsLayout.addComponent(cancelBtn);
		mainLayout.addComponent(actionsLayout);
		mainLayout.setComponentAlignment(actionsLayout, Alignment.BOTTOM_LEFT);
	}

	/**
	 * Generate components.
	 * 
	 * @return The layout
	 */
	public abstract Layout generateContent();

	/**
	 * Save action.
	 * 
	 * @param tableInfo
	 *            The table info
	 */
	public abstract void saveAction(TableInfo tableInfo);

	/**
	 * Gets the identification.
	 * 
	 * @return The identification
	 */
	public final Integer getIdentification() {
		if ("".equals(txtId.getValue())) {
			return null;
		}
		return Integer.parseInt(txtId.getValue());
	}

	/**
	 * Sets the identification.
	 * 
	 * @param value
	 *            The new identification
	 */
	public final void setIdentification(final Integer value) {
		txtId.setReadOnly(false);
		txtId.setValue(value + "");
		txtId.setReadOnly(true);
	}

}
