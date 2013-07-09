package com.mmsh.vaadin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyColumnAnnot {
	public String id() default "";
	public String name() default "";
	public boolean isSearchable() default true;
	public boolean isExactMatch() default true;
	public boolean isIgnoreCase() default true;
	public int width() default -1;
}
