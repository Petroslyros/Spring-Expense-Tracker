package com.spring.expenses.expensestracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseInsertDTO(
        @NotNull
        String title,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        LocalDateTime date,

        Long expenseCategoryId,  // Changed from Category enum to category ID

        @NotNull
        Long userId
) {}