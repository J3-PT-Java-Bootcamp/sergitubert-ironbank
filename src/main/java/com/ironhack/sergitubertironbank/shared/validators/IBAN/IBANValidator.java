package com.ironhack.sergitubertironbank.shared.validators.IBAN;

import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IBANValidator implements ConstraintValidator<IBAN, String> {

    @Override
    public void initialize(IBAN constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        try {
            IbanUtil.validate(value);
            return true;
        } catch (IbanFormatException |
                 InvalidCheckDigitException |
                 UnsupportedCountryException exception) {
            return false;
        }
    }
}
