package org.vaadin.jpatablegenerator.layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.vaadin.jpatablegenerator.MyUI;
import org.vaadin.jpatablegenerator.annotations.MyColumn;
import org.vaadin.jpatablegenerator.components.MyEdit;
import org.vaadin.jpatablegenerator.components.MyNumberField;
import org.vaadin.jpatablegenerator.components.MyTextField;
import org.vaadin.jpatablegenerator.table.TableInfo;
import org.vaadin.jpatablegenerator.table.autogenerate.GenerateTableInfo;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
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
	private List<Field> fields = new ArrayList<Field>();
	private FormLayout formLayout;

	private boolean hasError = false;

	public GeneratedEdit(final TableInfo tableInfo, final Object itemId) {
		super(tableInfo);

		EntityItem<?> entityItem = null;
		if (itemId != null) {
			entityItem = tableInfo.getJpaContainer().getItem(itemId);
			setIdentification(Integer.parseInt(itemId.toString()));
		}

		for (Field f : tableInfo.getClazz().getDeclaredFields()) {
			org.vaadin.jpatablegenerator.annotations.MyEdit et = f.getAnnotation(org.vaadin.jpatablegenerator.annotations.MyEdit.class);
			Column c = f.getAnnotation(Column.class);
			MyColumn mc = f.getAnnotation(MyColumn.class);
			Id id = f.getAnnotation(Id.class);
			if (id == null && et != null && (c != null || mc != null) ) {

				boolean required;
				if (c == null) {
					JoinColumn jc = f.getAnnotation(JoinColumn.class);
					required = jc.nullable();
				} else {
					required = !c.nullable();
				}

				if (et.componentType().equals(com.vaadin.ui.AbstractField.class)) {
					AbstractField field = null;
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
						field.addValueChangeListener(new Property.ValueChangeListener() {
							private static final long serialVersionUID = -4112453798709845208L;

							@Override
							public void valueChange(ValueChangeEvent event) {
								hasError = false;
							}
						});
						components.add(field);
						fieldTypes.add(f.getType());
						fields.add(f);
						formLayout.addComponent(field);
					}
				} else {
					if (et.id().contains(".")) {
						if (et.componentType().equals(com.vaadin.ui.ComboBox.class)) {
							GenerateTableInfo gti = new GenerateTableInfo(f.getType(), false, required);

							ComboBox field = new ComboBox();
							field.setContainerDataSource(gti.getJpaContainer());

							field.setFilteringMode(FilteringMode.CONTAINS);

							String[] strArray = et.itemCaption().split("\\.");
							field.setItemCaptionPropertyId(strArray[strArray.length - 1]);

							field.setImmediate(false);
							field.setRequired(required);
							field.setNullSelectionAllowed(required);
							if (required) {
								if ("".equals(et.requiredMessage())) {
									field.setRequiredError("The field shall not be empty");
								} else {
									field.setRequiredError(et.requiredMessage());
								}
							}
							if (entityItem != null) {
								field.setPropertyDataSource(entityItem.getItemProperty(et.id()));
								field.select(entityItem.getItemId());
							}
							components.add(field);
							fieldTypes.add(f.getType());
							formLayout.addComponent(field);
							fields.add(f);
						}
					}
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
						Class fieldType = fieldTypes.get(i);
						if (fieldType.equals(Integer.class)) {
							if (val == null || "".equals(val.toString())) {
								list.add(null);
							} else {
								list.add(Integer.parseInt(val.toString()));
							}
						} else if (fieldType.equals(Double.class)) {
							if (val == null || "".equals(val.toString())) {
								list.add(null);
							} else {
								list.add(Double.parseDouble(val.toString()));
							}
						} else if (fieldType.equals(Long.class)) {
							if (val == null || "".equals(val.toString())) {
								list.add(null);
							} else {
								list.add(Long.parseLong(val.toString()));
							}
						} else if (fieldType.equals(String.class)) {
							list.add(val);
						} else {
							org.vaadin.jpatablegenerator.annotations.MyEdit et = fields.get(i).getAnnotation(org.vaadin.jpatablegenerator.annotations.MyEdit.class);
							if (et != null && et.id().contains(".")) {
								Object fcObject = fieldType.getConstructor().newInstance();
								String[] strArray = et.id().split("\\.");
								String ssttrr = strArray[1].substring(0,1).toUpperCase() + strArray[1].substring(1);
								fcObject.getClass().getMethod("set" + ssttrr, val.getClass()).invoke(fcObject, val);
								list.add(fcObject);
							}
						}
						i++;
					}
					Integer id = (Integer) tableInfo.getJpaContainer().addEntity(c.newInstance(list.toArray(new Object[list.size()])));
					setIdentification(id);

					if (!hasError) {
						((MyUI) UI.getCurrent()).removeActiveEditPopupWindow();
						Notification.show("Saved successfully");
					}

				} catch (NoSuchMethodException | SecurityException | UnsupportedOperationException | IllegalStateException
						| InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
					handleError(e.getCause());
				}
			} else {
				if (!hasError) {
					((MyUI) UI.getCurrent()).removeActiveEditPopupWindow();
					Notification.show("Saved successfully" + getErrorMessage());
				}
			}
		}
	}

	private void handleError(Throwable throwable) {
        String cause = "";
        for (Throwable t = throwable; t != null; t = t.getCause()) {
            if (t.getCause() == null) {
            	// We're at final cause
                cause += t.getMessage();
            }
        }
        // Display the error message in a custom fashion
        Notification.show(cause, Type.ERROR_MESSAGE);
        hasError = true;
	}

	@Override
	public final Layout generateContent() {
		formLayout = new FormLayout();
		formLayout.setImmediate(true);
		formLayout.setMargin(true);
		formLayout.setSpacing(true);

		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			private static final long serialVersionUID = 6120431091175509219L;

			@Override
		    public void error(com.vaadin.server.ErrorEvent event) {
				handleError(event.getThrowable());
		    }
		});


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
