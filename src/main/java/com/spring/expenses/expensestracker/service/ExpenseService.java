package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.exceptions.ExpenseNotFoundException;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;
import com.spring.expenses.expensestracker.mapper.Mapper;
import com.spring.expenses.expensestracker.model.Expense;
import com.spring.expenses.expensestracker.model.ExpenseCategory;
import com.spring.expenses.expensestracker.model.User;
import com.spring.expenses.expensestracker.repository.ExpensesRepository;
import com.spring.expenses.expensestracker.repository.ExpenseCategoryRepository;
import com.spring.expenses.expensestracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService implements IExpenseService {

    private final ExpensesRepository expensesRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public ExpenseReadOnlyDTO createExpense(ExpenseInsertDTO expenseInsertDTO) throws UserNotFoundException {
        User user = userRepository.findById(expenseInsertDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(expenseInsertDTO.userId()));

        ExpenseCategory category = null;
        if (expenseInsertDTO.expenseCategoryId() != null) {
            category = expenseCategoryRepository.findById(expenseInsertDTO.expenseCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        Expense expense = mapper.mapExpenseInsertDTOToExpense(expenseInsertDTO, user, category);
        Expense savedExpense = expensesRepository.save(expense);

        return mapper.mapExpenseToExpenseReadOnlyDTO(savedExpense);
    }

    @Override
    public List<ExpenseReadOnlyDTO> getAllExpenses() {
        return expensesRepository.findAll().stream()
                .map(mapper::mapExpenseToExpenseReadOnlyDTO)
                .toList();
    }

    @Override
    public List<ExpenseReadOnlyDTO> getExpenseByUserId(Long userId) throws UserNotFoundException {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Expense> expenses = expensesRepository.findByUserId(userId);
        return expenses.stream()
                .map(mapper::mapExpenseToExpenseReadOnlyDTO)
                .toList();
    }

    @Override
    public ExpenseReadOnlyDTO updateExpense(Long id, ExpenseInsertDTO expenseInsertDTO)
            throws ExpenseNotFoundException, UserNotFoundException {

        Expense existingExpense = expensesRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        User user = userRepository.findById(expenseInsertDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(expenseInsertDTO.userId()));

        existingExpense.setTitle(expenseInsertDTO.title());
        existingExpense.setAmount(expenseInsertDTO.amount());
        existingExpense.setDate(expenseInsertDTO.date());

        // Handle category change
        if (expenseInsertDTO.expenseCategoryId() != null) {
            ExpenseCategory category = expenseCategoryRepository.findById(expenseInsertDTO.expenseCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingExpense.setExpenseCategory(category);
        } else {
            existingExpense.setExpenseCategory(null);
        }

        existingExpense.setUser(user);
        Expense updatedExpense = expensesRepository.save(existingExpense);

        return mapper.mapExpenseToExpenseReadOnlyDTO(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) throws ExpenseNotFoundException {
        Expense expense = expensesRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        // Soft delete - set isDeleted flag
        expense.setIsDeleted(true);
        expensesRepository.save(expense);
    }

    @Override
    public List<ExpenseReadOnlyDTO> getExpenseByUserIdAndCategory(Long userId, Long categoryId)
            throws UserNotFoundException {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Expense> expenses = expensesRepository.findByUserIdAndExpenseCategoryId(userId, categoryId);
        return expenses.stream()
                .map(mapper::mapExpenseToExpenseReadOnlyDTO)
                .toList();
    }
}