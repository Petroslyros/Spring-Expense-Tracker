package com.spring.expenses.expensestracker.dto;

import com.spring.expenses.expensestracker.core.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReadOnlyDTO {

    private Long id;
    private String title;
    private BigDecimal amount;
    private LocalDateTime date;

}
