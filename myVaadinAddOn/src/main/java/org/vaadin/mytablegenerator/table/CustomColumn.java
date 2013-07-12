package org.vaadin.mytablegenerator.table;

import java.io.Serializable;

import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.ColumnGenerator;

/**
 * Description: Generates a column for the table with the given attributes.<br>
 * <br>
 * Filename: GeneratedColumn.java <br>
 * 
 * @since 18.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision: 151 $ <br>
 *          $LastChangedDate: 2013-06-20 12:39:55 +0200 (Do, 20 Jun 2013) $
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
public class CustomColumn implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 457379555435587717L;
	
	/** The column name. */
	private String columnName;
	
	/** The column width. */
	private int columnWidth = DEFAULT_COLUMN_WIDTH;
	
	/** The column alignment. */
	private Align columnAlignment;
	
	/** The column. */
	private ColumnGenerator column;

	/** The Constant DEFAULT_COLUMN_WIDTH. */
	private static final int DEFAULT_COLUMN_WIDTH = 90;
	/**
	 * Gets the column width.
	 * 
	 * @return the column width
	 */
	public final int getColumnWidth() {
		return columnWidth;
	}

	/**
	 * Sets the column width.
	 * 
	 * @param pColumnWidth The new column width
	 */
	public final void setColumnWidth(final int pColumnWidth) {
		this.columnWidth = pColumnWidth;
	}

	/**
	 * Gets the column alignment.
	 * 
	 * @return the column alignment
	 */
	public final Align getColumnAlignment() {
		return columnAlignment;
	}

	/**
	 * Sets the column alignment.
	 * 
	 * @param center The new column alignment
	 */
	public final void setColumnAlignment(final Align center) {
		this.columnAlignment = center;
	}
	
	/**
	 * Instantiates a new GeneratedColumn object.
	 * 
	 * @param pColumnName The column name
	 */
	public CustomColumn(final String pColumnName) {
		this.columnName = pColumnName;
	}
	
	/**
	 * Gets the column name.
	 * 
	 * @return the column name
	 */
	public final String getColumnName() {
		return columnName;
	}
	
	/**
	 * Sets the column name.
	 * 
	 * @param pColumnName The new column name
	 */
	public final void setColumnName(final String pColumnName) {
		this.columnName = pColumnName;
	}
	
	/**
	 * Gets the column.
	 * 
	 * @return the column
	 */
	public final ColumnGenerator getColumn() {
		return column;
	}
	
	/**
	 * Sets the column.
	 * 
	 * @param pColumn The new column
	 */
	public final void setColumn(final ColumnGenerator pColumn) {
		this.column = pColumn;
	}
}
