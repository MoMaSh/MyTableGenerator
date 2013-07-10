package org.mmsh.vaadin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mmsh.vaadin.components.MyTextField;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyEditTypeAnnot {
	public String caption();
	public Class<?> componentType() default MyTextField.class;
}
