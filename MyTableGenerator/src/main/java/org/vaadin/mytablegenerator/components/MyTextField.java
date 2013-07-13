package org.vaadin.mytablegenerator.components;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

/**
 * Description: This component is made out of {@link TextField}.<br>
 * <br>
 * Filename: MyNumberField.java <br>
 * 
 * @since 20.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision$ <br>
 *          $LastChangedDate$
 * @author <a href="mailto:m.m.shahabi@gmail.com">$Author: mohammad.shahabi $</a><br>
 */
public class MyTextField extends TextField {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5786602410609267935L;
	
	public MyTextField(String caption, boolean required, String requiredMsg, int length, String validationRegex, String validationMsg) {
		super(caption);
		setNullRepresentation("");
		
		int min = 0;
		if (required) {
			min = 1;
		}
		String lengthRegex = "((?s).){" + min + "," + length + "}";
		this.addValidator(new RegexpValidator(lengthRegex, requiredMsg));
		
		this.addValidator(new RegexpValidator(validationRegex, validationMsg));
	}

}
