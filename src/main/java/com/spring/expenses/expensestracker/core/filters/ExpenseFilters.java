package com.spring.expenses.expensestracker.core.filters;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExpenseFilters extends GenericFilters {
    @Nullable
    private Long userId;

    @Nullable
    private Long categoryId;

    @Nullable
    private String title;
}