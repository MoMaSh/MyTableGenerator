package com.mmsh.vaadin.components;

import org.vaadin.csvalidation.CSValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

/**
 * Description: This component is made out of {@link TextField}.<br>
 * <br>
 * Company: <a href="http://www.osb-ag.de">OSB AG</a> <br>
 * Filename: MyNumberField.java <br>
 * 
 * @since 20.06.2013 <br>
 * @version <br>
 *          $LastChangedRevision$ <br>
 *          $LastChangedDate$
 * @author <a href="mailto:m.shahabi@osb-ag.de">$Author$</a><br>
 */
public class MyNumberField extends TextField {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7390942553316386175L;

	/**
	 * Instantiates a new MyNumberField object.
	 * 
	 * @param caption
	 *            The caption
	 * @param validationMessage
	 *            The validation message
	 * @param required 
	 */
	public MyNumberField(final String caption, final boolean required) {
		this.setCaption(caption);
		CSValidator validator = new CSValidator();
		validator.extend(this);
		String regexp = "\\d{0,11}";
		if (required) {
			regexp = "\\d{1,11}";
			this.setRequired(true);
			this.setRequiredError("Eintrag darf nicht leer sein.");
		}
		validator.setRegExp(regexp);
		validator.setErrorMessage("muss eine Zahl sein");
		this.addValidator(new RegexpValidator(regexp, "muss eine Zahl sein"));
		this.setImmediate(true);
	}

	
}
