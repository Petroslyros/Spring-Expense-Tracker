package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.enums.Category;
import com.spring.expenses.expensestracker.core.exceptions.ExpenseNotFoundException;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;
import com.spring.expenses.expensestracker.mapper.Mapper;
import com.spring.expenses.expensestracker.model.Expense;
import com.spring.expenses.expensestracker.model.User;
import com.spring.expenses.expensestracker.repository.ExpensesRepository;
import com.spring.expenses.expensestracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService implements IExpenseService {

    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;


    @Override
    public ExpenseReadOnlyDTO createExpense(ExpenseInsertDTO expenseInsertDTO) {
        User user = userRepository.findById(expenseInsertDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Expense expense = mapper.expenseReadOnlyToEntity(expenseInsertDTO,user);
        expense.setUser(user);

        Expense savedExpense = expensesRepository.save(expense);

        return mapper.expenseEntityToReadOnlyDTO(savedExpense);
    }

    @Override
    public List<ExpenseReadOnlyDTO> getAllExpenses() {
        return expensesRepository.findAll().stream()
                .map(mapper::expenseEntityToReadOnlyDTO)
                .toList();
    }

    @Override
    public List<ExpenseReadOnlyDTO> getExpenseByUserId(Long userId) {
        List<Expense> expenses = expensesRepository.findByUserId(userId);
        return expenses.stream()
                .map(mapper::expenseEntityToReadOnlyDTO)
                .toList();
    }

    @Override
    public ExpenseReadOnlyDTO updateExpense(Long id, ExpenseInsertDTO expenseInsertDTO) throws ExpenseNotFoundException, UserNotFoundException {
        Expense existingExpense = expensesRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id)); // if expense exists it is stored in existingExpense

        User user = userRepository.findById(expenseInsertDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(id));

        existingExpense.setTitle(expenseInsertDTO.title());
        existingExpense.setAmount(expenseInsertDTO.amount());
        existingExpense.setCategory(expenseInsertDTO.category());
        existingExpense.setDate(expenseInsertDTO.date());
        existingExpense.setUser(user);

        Expense updatedExpense = expensesRepository.save(existingExpense);
        return mapper.expenseEntityToReadOnlyDTO(updatedExpense);

    }

    @Override
    public void deleteExpense(Long id) throws ExpenseNotFoundException {
        Expense expense = expensesRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        expensesRepository.delete(expense);
    }

    @Override
    public List<ExpenseReadOnlyDTO> getExpenseByUserIdAndCategory(Long userId, Category category) {
        List<Expense> expenses = expensesRepository.findByUserIdAndCategory(userId,category);

        return expenses.stream()
                .map(mapper::expenseEntityToReadOnlyDTO)
                .toList();
    }
}
