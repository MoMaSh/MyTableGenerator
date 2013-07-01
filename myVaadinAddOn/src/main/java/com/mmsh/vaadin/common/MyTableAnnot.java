package com.mmsh.vaadin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyTableAnnot {
	public String caption();
	public String persistanceName();
	public int height();
	public int width();
	public String popupCaption();
}