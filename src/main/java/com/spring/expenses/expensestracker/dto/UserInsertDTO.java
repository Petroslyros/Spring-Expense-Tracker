package com.spring.expenses.expensestracker.dto;

import com.spring.expenses.expensestracker.core.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(
        @NotNull
        @Size(min = 2, max = 20)
        String username,

        @NotNull
        String email,

        @NotNull
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$")
        String password,

        @NotNull
        String firstname,

        @NotNull
        String lastname,

        @NotNull
        UserRole userRole
) {}