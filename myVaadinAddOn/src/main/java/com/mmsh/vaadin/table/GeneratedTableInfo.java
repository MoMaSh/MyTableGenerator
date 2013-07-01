package com.mmsh.vaadin.table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.mmsh.vaadin.MyUI;
import com.mmsh.vaadin.common.MyColumnAnnot;
import com.mmsh.vaadin.common.MyColumnExtendedAnnot;
import com.mmsh.vaadin.common.MyDefalutColumnAnnot;
import com.mmsh.vaadin.common.MyTableAnnot;
import com.mmsh.vaadin.layout.GeneratedEdit;
import com.mmsh.vaadin.windows.EditPopupWindow;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;


/**
 * Description: The specific information about each {@link MyTable} that is supposed to be created.<br>
 * The visible columns, generated columns, searchability of each column, ...<br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: TableInfo.java <br>
 * 
 * @since 18.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 151 $ <br>
 *          $LastChangedDate: 2013-06-20 12:39:55 +0200 (Do, 20 Jun 2013) $
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author: mohammad.shahabi $</a><br>
 */
public class GeneratedTableInfo extends TableInfo {
	private static final long serialVersionUID = -28713779327451123L;

	public GeneratedTableInfo(Class<?> clazz, boolean shallGenerate) {
		
		this.setClazz(clazz);
		
		MyTableAnnot classAnnotation = clazz.getAnnotation(MyTableAnnot.class);
		this.setCaption(classAnnotation.caption());
		this.setJpaContainer(JPAContainerFactory.make(clazz, classAnnotation.persistanceName()));
		
		this.setPopupCaption(classAnnotation.popupCaption());
		this.setPopupWidth(classAnnotation.width());
		this.setPopupHeight(classAnnotation.height());

		if (shallGenerate) {
			List<MyColumn> l = new ArrayList<MyColumn>();	
			List<String> nested = new ArrayList<String>();
			for (Field f : clazz.getDeclaredFields()) {
				for (Annotation a : f.getAnnotations()) {
					if (a instanceof MyDefalutColumnAnnot) {
						l.add(new MyColumn(f.getName()));
					} else if (a instanceof MyColumnAnnot) {
						MyColumnAnnot mc = (MyColumnAnnot) a;
						l.add(new MyColumn(mc.id(), mc.name()));
						if (mc.id().contains(".")) {
							nested.add(mc.id());
						}
					} else if (a instanceof MyColumnExtendedAnnot) {
						MyColumnExtendedAnnot mc = (MyColumnExtendedAnnot) a;
						MyColumn osbColumn = new MyColumn(mc.id(), mc.name(), mc.isSearchable(), mc.isExactMatch(), mc.isIgnoreCase(), mc.width());
						l.add(osbColumn);
						if (mc.id().contains(".")) {
							nested.add(mc.id());
						}
					}
				}
			}
			this.setColumns(l);
			this.setNestedProperties(nested.toArray(new String[nested.size()]));
		}
	}
	
	@Override
	public void defineEdit(Object itemId, boolean duplicate) {
		((MyUI) UI.getCurrent()).showEditPopup(new EditPopupWindow(this, new GeneratedEdit(this, itemId)));
	}

	@Override
	public Component getNewEntity() {
		return new GeneratedEdit(this, null);
	}
}
