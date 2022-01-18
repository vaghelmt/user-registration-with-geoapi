package com.vaghelmt.geoapi.validators;

import org.passay.*;

import com.google.common.base.Joiner;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                //TODO: only allow special characters in this list [_ # $ % .]
                new CharacterRule(EnglishCharacterData.Special,1),
                new CharacterRule(EnglishCharacterData.Digit, 1)));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}