package org.vaadin.mytablegenerator.layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.vaadin.mytablegenerator.MyUI;
import org.vaadin.mytablegenerator.components.MyEdit;
import org.vaadin.mytablegenerator.components.MyNumberField;
import org.vaadin.mytablegenerator.components.MyTextField;
import org.vaadin.mytablegenerator.table.TableInfo;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Description: The edit page for {@link Typ} which is of type {@link MyEdit2}.<br>
 * <br>
 * Filename: EditTyp.java <br>
 * 
 * @since 19.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 159 $ <br>
 *          $LastChangedDate: 2013-06-21 14:10:25 +0200 (Fr, 21 Jun 2013) $
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
@SuppressWarnings("rawtypes")
public class GeneratedEdit extends MyEdit {
	
	private static final long serialVersionUID = 1736627586496278968L;
	
	private List<AbstractField> components = new ArrayList<AbstractField>();
	private List<Class> fieldTypes = new ArrayList<Class>(); 
	private FormLayout formLayout;
	
	public GeneratedEdit(final TableInfo tableInfo, final Object itemId) {
		super(tableInfo);
		
		EntityItem<?> entityItem = null;
		if (itemId != null) {
			entityItem = tableInfo.getJpaContainer().getItem(itemId);
			setIdentification(Integer.parseInt(itemId.toString()));
		}
		
		for (Field f : tableInfo.getClazz().getDeclaredFields()) {
			org.vaadin.mytablegenerator.annotations.MyEdit et = f.getAnnotation(org.vaadin.mytablegenerator.annotations.MyEdit.class);
			Column c = f.getAnnotation(Column.class);
			Id id = f.getAnnotation(Id.class);
			if (id == null && et != null && c != null ) {
				
				AbstractField field = null;
				boolean required = !c.nullable();
				
				if (et.componentType().equals(com.vaadin.ui.AbstractField.class)) {
					if (f.getType() == String.class) {
						
						String validationMsg = "Invalid String value";
						if (!"".equals(et.validationMessage())) {
							validationMsg = et.validationMessage();
						}
						
						String requiredMsg = "Field cannot be empty";
						if (!"".equals(et.requiredMessage())) {
							requiredMsg = et.requiredMessage();
						}
						
						String validationRegex = "(?=\\s*\\S).*";
						if (!"".equals(et.validationRegex())) {
							validationRegex = et.validationRegex();
						}
						
						field = new MyTextField(et.caption(), required, requiredMsg, c.length(), validationRegex, validationMsg);
						
					} else if (f.getType() == Integer.class) {

						String validationMsg = "Invalid Integer value";
						if (!"".equals(et.validationMessage())) {
							validationMsg = et.validationMessage();
						}
						
						String requiredMsg = "Field cannot be empty";
						if (!"".equals(et.requiredMessage())) {
							requiredMsg = et.requiredMessage();
						}
						
						String validationRegex = "(\\d+)|(\\d+[\\.,]\\d+)*";
						if (!"".equals(et.validationRegex())) {
							validationRegex = et.validationRegex();
						}
						
						field = new MyNumberField(et.caption(), required, requiredMsg, c.length(), validationRegex, validationMsg, et.format());
						
					} else if (f.getType() == Boolean.class) {
						field = new CheckBox(et.caption());
					} else if (f.getType() == Date.class) {
						field = new DateField(et.caption());
					} else {
						field = new TextField(et.caption());
					}
					
				} else {
					if (et.componentType() == TextField.class) {
						field = new TextField(et.caption());
						field.setRequired(!c.nullable());
					} else if (et.componentType() == DateField.class) {
						field = new DateField(et.caption());
					}
				}
				
				if (field != null) {
					field.setImmediate(false);
					field.setRequired(required);
					if (required) {
						if ("".equals(et.requiredMessage())) {
							field.setRequiredError("The field shall not be empty");
						} else {
							field.setRequiredError(et.requiredMessage());
						}
					}
					if (entityItem != null) {
						field.setPropertyDataSource(entityItem.getItemProperty(c.name()));
					}
					components.add(field);
					fieldTypes.add(f.getType());
					formLayout.addComponent(field);
				}
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void saveAction(final TableInfo tableInfo) {
		if (checkValidation()) {
			if (getIdentification() == null) {
				try {
					Constructor c = tableInfo.getClazz().getConstructor(fieldTypes.toArray(new Class[fieldTypes.size()]));
					List<Object> list = new ArrayList<Object>();
					int i = 0;
					for (Object o : components) {
						Object val = o.getClass().getMethod("getValue").invoke(o);
						if (fieldTypes.get(i).equals(Integer.class)) {
							if (val == null || "".equals(val.toString())) {
								list.add(null);
							} else {
								list.add(Integer.parseInt(val.toString()));
							}
						} else {
							list.add(val);
						}
						i++;
					}
					Integer id = (Integer) tableInfo.getJpaContainer().addEntity(c.newInstance(list.toArray(new Object[list.size()])));
					setIdentification(id);
//					((MyUI) UI.getCurrent()).removeActiveEditPopupWindow();
//					Notification.show("Saved successfully");
				} catch (NoSuchMethodException | SecurityException | UnsupportedOperationException | IllegalStateException
						| InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
//			} else {
				//						EntityItem<?> ei = tableInfo.getJpaContainer().getItem(getIdentification());
				//						ei.commit();
//				((MyUI) UI.getCurrent()).removeActiveEditPopupWindow();
//				Notification.show("Saved successfully" + getErrorMessage());
			}
		}
	}

	@Override
	public final Layout generateContent() {
		formLayout = new FormLayout();
		formLayout.setImmediate(true);
		formLayout.setMargin(true);
		formLayout.setSpacing(true);
		return formLayout;
	}

	/**
	 * Check validation.
	 * 
	 * @return true, if successful
	 */
	private boolean checkValidation() {
		try {
			for (AbstractField field : components) {
				if (!field.isValid()) {
					Notification.show("Validation failed " + field.getCaption(), Type.WARNING_MESSAGE);
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
