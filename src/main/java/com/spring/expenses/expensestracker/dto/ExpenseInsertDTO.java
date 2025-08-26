package com.spring.expenses.expensestracker.dto;

import com.spring.expenses.expensestracker.core.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseInsertDTO(

        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        BigDecimal amount,
        Category category,
        LocalDateTime date,
        Long userId

) {
}
