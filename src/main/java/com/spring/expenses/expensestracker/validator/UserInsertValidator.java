package com.spring.expenses.expensestracker.validator;

import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserInsertDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInsertDTO userInsertDTO = (UserInsertDTO) target;

        // Validate firstname
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "firstname",
                "empty",
                "Firstname cannot be blank"
        );
        if (userInsertDTO.firstname().length() < 2 || userInsertDTO.firstname().length() > 50) {
            errors.rejectValue("firstname", "size", null, "Firstname must be between 2 and 50 characters");
        }

        // Validate lastname
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "lastname",
                "empty",
                "Lastname cannot be blank"
        );
        if (userInsertDTO.lastname().length() < 2 || userInsertDTO.lastname().length() > 50) {
            errors.rejectValue("lastname", "size", null, "Lastname must be between 2 and 50 characters");
        }

        // Validate username
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "username",
                "empty",
                "Username cannot be blank"
        );
        if (userInsertDTO.username().length() < 3 || userInsertDTO.username().length() > 20) {
            errors.rejectValue("username", "size", null, "Username must be between 3 and 20 characters");
        }

        // Validate email
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "email",
                "empty",
                "Email cannot be blank"
        );
        if (!userInsertDTO.email().contains("@")) {
            errors.rejectValue("email", "invalid", null, "Email must be a valid email address");
        }
    }
}