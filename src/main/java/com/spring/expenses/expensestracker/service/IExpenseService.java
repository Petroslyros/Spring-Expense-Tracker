package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.exceptions.ExpenseNotFoundException;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;

import java.util.List;

public interface IExpenseService {
    ExpenseReadOnlyDTO createExpense(ExpenseInsertDTO expenseInsertDTO) throws UserNotFoundException;
    List<ExpenseReadOnlyDTO> getAllExpenses();
    List<ExpenseReadOnlyDTO> getExpenseByUserId(Long userId) throws UserNotFoundException;
    ExpenseReadOnlyDTO updateExpense(Long id, ExpenseInsertDTO expenseInsertDTO) throws ExpenseNotFoundException, UserNotFoundException;
    void deleteExpense(Long id) throws ExpenseNotFoundException;
    List<ExpenseReadOnlyDTO> getExpenseByUserIdAndCategory(Long userId, Long categoryId) throws UserNotFoundException;
}