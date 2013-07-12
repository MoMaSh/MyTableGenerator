package org.vaadin.mytablegenerator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vaadin.ui.AbstractField;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyEdit {
	public String caption() default "";
	public Class<?> componentType() default AbstractField.class;
}
