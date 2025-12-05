package com.spring.expenses.expensestracker.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseReadOnlyDTO(
        Long id,
        String title,
        BigDecimal amount,
        LocalDateTime date,
        String categoryName,  // Category name instead of enum
        LocalDateTime insertedAt,
        LocalDateTime modifiedAt
) {}