package com.spring.expenses.expensestracker.dto;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {
    private String username;
    private String token;
}
