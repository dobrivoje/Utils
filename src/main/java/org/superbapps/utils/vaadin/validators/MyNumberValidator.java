package org.superbapps.utils.vaadin.validators;

import com.vaadin.v7.data.Validator;

/**
 * @author д06ри
 */
public class MyNumberValidator implements Validator {

    @Override
    public void validate(Object value) throws InvalidValueException {
        String text = (String) value;

        if (text == null || text.trim().isEmpty()) {
            throw new InvalidValueException("Must enter at least zero !");
        }
        if (!text.matches("\\d*")) {
            throw new InvalidValueException("Just numbers allowed !");
        }
    }

}
