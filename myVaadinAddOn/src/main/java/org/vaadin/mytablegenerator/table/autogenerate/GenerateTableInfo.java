package org.vaadin.mytablegenerator.table.autogenerate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vaadin.mytablegenerator.annotations.MyTable;
import org.vaadin.mytablegenerator.common.MyUtil;
import org.vaadin.mytablegenerator.components.MyEdit;
import org.vaadin.mytablegenerator.components.MyImport;
import org.vaadin.mytablegenerator.layout.GeneratedEdit;
import org.vaadin.mytablegenerator.table.MyColumn;
import org.vaadin.mytablegenerator.table.TableInfo;

import com.vaadin.addon.jpacontainer.JPAContainerFactory;


/**
 * Description: The specific information about each {@link MyTable} that is supposed to be created.<br>
 * The visible columns, generated columns, searchability of each column, ...<br>
 * Filename: TableInfo.java <br>
 * 
 * @since 18.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 151 $ <br>
 *          $LastChangedDate: 2013-06-20 12:39:55 +0200 (Do, 20 Jun 2013) $
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
public class GenerateTableInfo extends TableInfo {
	private static final long serialVersionUID = -28713779327451123L;

	public GenerateTableInfo(Class<?> clazz, boolean shallGenerate, boolean includeId) {
		
		this.setClazz(clazz);
		
		MyTable myTable = clazz.getAnnotation(MyTable.class);
		Table table = clazz.getAnnotation(Table.class);

		String caption = "";
		String popupCaption = "";
		String persistenceName = "";
		int width = -1;
		int height = -1;
		
		if (myTable != null) {
			caption = myTable.caption();
			popupCaption = myTable.popupCaption();
			width = myTable.width();
			height = myTable.height();
			persistenceName = myTable.persistanceName();
			if ("".equals(persistenceName)) {
				persistenceName = table.catalog();
			}
		} else {
			popupCaption = MyUtil.getCaption(table.name());
			caption = popupCaption + "s";
			// TODO better to find a way to get width and height from MyTable annotation 
			width = 300;
			height = 200;
			persistenceName = table.catalog();
		}
		
		this.setCaption(caption);
		this.setPopupEditCaption(popupCaption);
		this.setPopupEditWidth(width);
		this.setPopupEditHeight(height);
		this.setJpaContainer(JPAContainerFactory.make(clazz, persistenceName));

		if (shallGenerate) {
			List<MyColumn> columnsList = new ArrayList<MyColumn>();	
			List<String> nested = new ArrayList<String>();
			
			boolean containsAnnot = false;
			for (Field f : clazz.getDeclaredFields()) {
				org.vaadin.mytablegenerator.annotations.MyColumn myColumn = f.getAnnotation(org.vaadin.mytablegenerator.annotations.MyColumn.class);
				if (myColumn != null) {
					MyColumn tableColumn;
					String id = myColumn.id();
					if ("".equals(id)) {
						id = f.getName();
					}
					String name = myColumn.name();
					if ("".equals(name)) {
						tableColumn = new MyColumn(id, myColumn.isSearchable(), myColumn.isExactMatch(), myColumn.isIgnoreCase(), myColumn.width());
					} else {
						tableColumn = new MyColumn(id, myColumn.name(), myColumn.isSearchable(), myColumn.isExactMatch(), myColumn.isIgnoreCase(), myColumn.width());
					}
					columnsList.add(tableColumn);
					if (myColumn.id().contains(".")) {
						nested.add(myColumn.id());
					}
					containsAnnot = true;
				}
			}
			
			if (!containsAnnot) {
				for (Field f : clazz.getDeclaredFields()) {
					Column column = f.getAnnotation(Column.class);
					Id id = f.getAnnotation(Id.class);
					// TODO better to find a way to get isSearchable, isExactMatch, isIgnoreCase and width from MyColumn annotation
					if (id != null && column != null && includeId) {
						columnsList.add(new MyColumn(f.getName(), MyUtil.getCaption(column.name()), true, false, true, -1));
					} else if (id == null && column != null) {
						columnsList.add(new MyColumn(f.getName(), MyUtil.getCaption(column.name()), true, false, true, -1));
					}
				}
			}

			this.setColumns(columnsList);
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
