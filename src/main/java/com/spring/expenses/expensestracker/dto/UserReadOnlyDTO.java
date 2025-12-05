package com.spring.expenses.expensestracker.dto;

import java.time.LocalDateTime;

public record UserReadOnlyDTO(
        Long id,
        String username,
        String email,
        String firstname,
        String lastname,
        LocalDateTime insertedAt,
        LocalDateTime modifiedAt
) {}

