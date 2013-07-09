package com.mmsh.vaadin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyTableAnnot {
	public String caption();
	public String popupCaption();
	public int height() default 100;
	public int width() default 300;
	public String persistanceName();
}