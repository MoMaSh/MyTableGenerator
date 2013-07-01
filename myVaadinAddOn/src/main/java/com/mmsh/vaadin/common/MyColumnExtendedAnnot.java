package com.mmsh.vaadin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyColumnExtendedAnnot {
	public String id();
	public String name();
	public boolean isSearchable();
	public boolean isExactMatch();
	public boolean isIgnoreCase();
	public int width();
}
