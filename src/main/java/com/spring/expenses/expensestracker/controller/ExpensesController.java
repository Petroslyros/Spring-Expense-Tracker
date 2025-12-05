package com.spring.expenses.expensestracker.controller;

import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;
import com.spring.expenses.expensestracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ExpensesController {

    private final ExpenseService expenseService;

    @GetMapping("/users/{userId}/expenses")
    public ResponseEntity<List<ExpenseReadOnlyDTO>> getExpensesPerUserId (@PathVariable Long userId) throws UserNotFoundException {
        List<ExpenseReadOnlyDTO> expenses = expenseService.getExpenseByUserId(userId);
        return ResponseEntity.ok(expenses);
    }


}
