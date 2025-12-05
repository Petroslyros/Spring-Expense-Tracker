package com.spring.expenses.expensestracker.validator;

import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class ExpenseInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ExpenseInsertDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExpenseInsertDTO expenseInsertDTO = (ExpenseInsertDTO) target;

        // Validate title
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "title",
                "empty",
                "Title cannot be blank"
        );
        if (expenseInsertDTO.title().length() < 3 || expenseInsertDTO.title().length() > 100) {
            errors.rejectValue("title", "size", null, "Title must be between 3 and 100 characters");
        }

        // Validate amount
        if (expenseInsertDTO.amount() == null) {
            errors.rejectValue("amount", "empty", null, "Amount cannot be null");
        } else if (expenseInsertDTO.amount().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("amount", "positive", null, "Amount must be greater than zero");
        }

        // Validate date
        if (expenseInsertDTO.date() == null) {
            errors.rejectValue("date", "empty", null, "Date cannot be null");
        }

        // Validate userId
        if (expenseInsertDTO.userId() == null) {
            errors.rejectValue("userId", "empty", null, "User ID cannot be null");
        }
    }
}