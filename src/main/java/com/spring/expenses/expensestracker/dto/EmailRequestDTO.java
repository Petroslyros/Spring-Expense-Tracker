package com.spring.expenses.expensestracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailRequestDTO {

    private String toEmail;
    private String subject;
    private String body;
}
