package org.vaadin.jpatablegenerator.components;

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
public class MyNumberField extends TextField {

	private static final long serialVersionUID = -7390942553316386175L;

	public MyNumberField(String caption, boolean required, String requiredMsg, int length, String validationRegex, String validationMsg, String format) {
		super(caption);
		setNullRepresentation("");

		// TODO Add the formatting here
		
		
		// FIXME These two regexes does not work
		
//		int minDigit = 0;
//		if (required) {
//			minDigit = 1;
//		}
//		int maxDigit = length + (length / 3);
//		String lengthRegex = "^\\S{" + minDigit + "," + maxDigit + "}$";
//		this.addValidator(new RegexpValidator(lengthRegex, requiredMsg));
		
//		this.addValidator(new RegexpValidator(validationRegex, validationMsg));
		
	}


}
