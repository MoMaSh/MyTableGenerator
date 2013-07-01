package com.mmsh.vaadin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyEditTypeAnnot {
	public String id();
	public Class<?> componentType();
	public String caption();
	public boolean required();
	public int max();
}
