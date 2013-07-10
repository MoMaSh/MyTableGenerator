package com.mmsh.vaadin.table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import com.mmsh.vaadin.common.MyColumnAnnot;
import com.mmsh.vaadin.common.MyTableAnnot;
import com.mmsh.vaadin.components.MyEdit;
import com.mmsh.vaadin.components.MyImport;
import com.mmsh.vaadin.layout.GeneratedEdit;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;


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
		Table table = clazz.getAnnotation(Table.class);
		
		this.setCaption(classAnnotation.caption());
		
		String persistenceName = classAnnotation.persistanceName();
		if ("".equals(persistenceName)) {
			persistenceName = table.catalog();
		}
		JPAContainer<?> jpa = JPAContainerFactory.make(clazz, persistenceName);
		this.setJpaContainer(jpa);
		
		this.setPopupCaption(classAnnotation.popupCaption());
		this.setPopupWidth(classAnnotation.width());
		this.setPopupHeight(classAnnotation.height());

		if (shallGenerate) {
			List<MyColumn> l = new ArrayList<MyColumn>();	
			List<String> nested = new ArrayList<String>();
			for (Field f : clazz.getDeclaredFields()) {
				for (Annotation annotation : f.getAnnotations()) {
					if (annotation instanceof MyColumnAnnot) {
						MyColumnAnnot mc = (MyColumnAnnot) annotation;

						MyColumn osbColumn;
						String id = mc.id();
						if ("".equals(id)) {
							id = f.getName();
						}
						String name = mc.name();
						if ("".equals(name)) {
							osbColumn = new MyColumn(id, mc.isSearchable(), mc.isExactMatch(), mc.isIgnoreCase(), mc.width());
						} else {
							osbColumn = new MyColumn(id, mc.name(), mc.isSearchable(), mc.isExactMatch(), mc.isIgnoreCase(), mc.width());
						}
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
	public MyEdit getEditComponent(Object itemId) {
		return new GeneratedEdit(this, itemId);
	}

	@Override
	public MyEdit getNewComponent() {
		return new GeneratedEdit(this, null);
	}

	@Override
	public MyImport getImportComponent() {
		return null;
	}

}
