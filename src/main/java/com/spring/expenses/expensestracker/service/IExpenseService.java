package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.enums.Category;
import com.spring.expenses.expensestracker.core.exceptions.ExpenseNotFoundException;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;
import com.spring.expenses.expensestracker.model.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IExpenseService {

    ExpenseReadOnlyDTO createExpense(ExpenseInsertDTO expenseInsertDTO);

    List<ExpenseReadOnlyDTO> getAllExpenses();

    List<ExpenseReadOnlyDTO> getExpenseByUserId(Long userId);

    ExpenseReadOnlyDTO updateExpense(Long expenseId, ExpenseInsertDTO expenseInsertDTO) throws ExpenseNotFoundException, UserNotFoundException;

    void deleteExpense(Long i) throws ExpenseNotFoundException;

    List<ExpenseReadOnlyDTO> getExpenseByUserIdAndCategory(Long userId, Category category);
}
