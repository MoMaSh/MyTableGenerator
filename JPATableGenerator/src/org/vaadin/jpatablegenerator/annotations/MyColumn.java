package org.vaadin.jpatablegenerator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyColumn {
	public String id() default "";
	public String name() default "";
	public boolean isSearchable() default true;
	public boolean isExactMatch() default false;
	public boolean isIgnoreCase() default true;
	public int width() default -1;
	public String format() default "";
}
