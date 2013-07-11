package org.mmsh.vaadin.components;

import org.vaadin.csvalidation.CSValidator;

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
	private static final long serialVersionUID = -7390942553316386175L;

	/**
	 * Instantiates a new MyNumberField object.
	 * 
	 * @param caption
	 *            The caption
	 * @param required
	 *            The required
	 * @param max
	 *            The max
	 */
	public MyTextField(final String caption, final boolean required, int max) {
		this(caption, required, true, max, "");
	}

	/**
	 * Instantiates a new MyNumberField object.
	 * 
	 * @param caption
	 *            The caption
	 * @param required
	 *            The required
	 * @param max
	 *            The max
	 */
	public MyTextField(final String caption, final boolean required,  final boolean acceptSpace, int max) {
		this(caption, required, acceptSpace, max, "");
	}

	/**
	 * Instantiates a new MyNumberField object.
	 * 
	 * @param caption
	 *            The caption
	 * @param required
	 *            The required
	 * @param max
	 *            The max
	 */
	public MyTextField(final String caption, final boolean required, int max, String msg) {
		this(caption, required, true, max, msg);
	}

	/**
	 * Instantiates a new MyNumberField object.
	 * 
	 * @param caption
	 *            The caption
	 * @param validationMessage
	 *            The validation message
	 * @param required 
	 */
	public MyTextField(final String caption, final boolean required, boolean acceptSpace, String msg) {
		this(caption, required, acceptSpace, 1000, msg);
	}
	
	/**
	 * Instantiates a new MyNumberField object.
	 * 
	 * @param caption
	 *            The caption
	 * @param validationMessage
	 *            The validation message
	 * @param required 
	 */
	public MyTextField(final String caption, final boolean required, boolean acceptSpace, int max, String msg) {
		this.setCaption(caption);
		CSValidator validator = new CSValidator();
		validator.extend(this);
		String regexp = "^.*$";
		if (!acceptSpace) {
			regexp = "\\w{0," + max + "}";
		}
		if (required) {
			regexp = "^.+$";
			if (!acceptSpace) {
				regexp = "\\w{1," + max + "}";
			}
			this.setRequired(true);
			this.setRequiredError("Eintrag darf nicht leer sein.");
		}
		validator.setRegExp(regexp);
		validator.setErrorMessage(msg);
		this.addValidator(new RegexpValidator(regexp, msg));
		this.setImmediate(true);
	}
}
