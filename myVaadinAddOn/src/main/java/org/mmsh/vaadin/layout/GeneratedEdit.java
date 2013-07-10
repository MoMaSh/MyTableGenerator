package org.mmsh.vaadin.layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.mmsh.vaadin.MyUI;
import org.mmsh.vaadin.common.MyEditTypeAnnot;
import org.mmsh.vaadin.common.MyUtil;
import org.mmsh.vaadin.components.MyEdit;
import org.mmsh.vaadin.components.MyTextField;
import org.mmsh.vaadin.table.TableInfo;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;

/**
 * Description: The edit page for {@link Typ} which is of type {@link MyEdit2}.<br>
 * <br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: EditTyp.java <br>
 * 
 * @since 19.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 159 $ <br>
 *          $LastChangedDate: 2013-06-21 14:10:25 +0200 (Fr, 21 Jun 2013) $
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author: mohammad.shahabi $</a><br>
 */
@SuppressWarnings("rawtypes")
public class GeneratedEdit extends MyEdit {
	
	private static final long serialVersionUID = 1736627586496278968L;
	
	private List<Component> components = new ArrayList<Component>();
	
	private List<Class> fieldTypes = new ArrayList<Class>(); 
	
	public GeneratedEdit(final TableInfo tableInfo, final Object itemId) {
		super(tableInfo);
		
		EntityItem<?> entityItem = null;
		if (itemId != null) {
			entityItem = tableInfo.getJpaContainer().getItem(itemId);
			setIdentification(Integer.parseInt(itemId.toString()));
		}
		
		
		
		for (Field f : tableInfo.getClazz().getDeclaredFields()) {
			MyEditTypeAnnot et = f.getAnnotation(MyEditTypeAnnot.class);
			Column c = f.getAnnotation(Column.class);
			if (et != null) {
				if (et.componentType() == MyTextField.class) {
					MyTextField txtField = new MyTextField(et.caption(), c.nullable(), c.length());
					if (entityItem != null) {
						txtField.setPropertyDataSource(entityItem.getItemProperty(c.name()));
					}
					components.add(txtField);
					fieldTypes.add(f.getType());
					formLayout.addComponent(txtField);
				} else if (et.componentType() == TextField.class) {
					TextField txtField = new TextField(et.caption());
					txtField.setRequired(c.nullable());
					if (entityItem != null) {
						txtField.setPropertyDataSource(entityItem.getItemProperty(c.name()));
					}
					components.add(txtField);
					fieldTypes.add(f.getType());
					formLayout.addComponent(txtField);
				} else if (et.componentType() == DateField.class) {
					DateField txtField = new DateField(et.caption());
					txtField.setRequired(c.nullable());
					if (entityItem != null) {
						txtField.setPropertyDataSource(entityItem.getItemProperty(c.name()));
					}
					components.add(txtField);
					fieldTypes.add(f.getType());
					formLayout.addComponent(txtField);
				} else if (et.componentType() == ColorPicker.class) {
					
					final ColorPicker colorPicker = new ColorPicker(et.caption());
					final TextField tf = new TextField();
					tf.setVisible(true);
					if (entityItem != null) {
						tf.setPropertyDataSource(entityItem.getItemProperty(c.name()));
					}
					colorPicker.setColor(MyUtil.hex2Rgb(tf.getValue()));
					colorPicker.addColorChangeListener(new ColorChangeListener() {
						private static final long serialVersionUID = 5526794258192880020L;

						@Override
						public void colorChanged(ColorChangeEvent event) {
							tf.setValue(colorPicker.getColor().getCSS());
						}
					});
					
					components.add(tf);
					fieldTypes.add(f.getType());

					
					formLayout.addComponent(tf);
					formLayout.addComponent(colorPicker);
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
					for (Object o : components) {
						list.add(o.getClass().getMethod("getValue", null).invoke(o, null));
					}
					Integer id = (Integer) tableInfo.getJpaContainer().addEntity(c.newInstance(list.toArray(new Object[list.size()])));
					setIdentification(id);
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} else {
				EntityItem<?> ei = tableInfo.getJpaContainer().getItem(getIdentification());
				ei.commit();
			}
			((MyUI) UI.getCurrent()).removeActiveEditPopupWindow();
			Notification.show("Saved successfully");
		}
	}

	FormLayout formLayout;
	
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
		return true;
	}

}