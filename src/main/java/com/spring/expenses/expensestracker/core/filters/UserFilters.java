package com.spring.expenses.expensestracker.core.filters;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserFilters extends GenericFilters {
    @Nullable
    private String username;

    @Nullable
    private String email;
}