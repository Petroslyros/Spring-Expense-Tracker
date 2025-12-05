package com.spring.expenses.expensestracker.repository;

import com.spring.expenses.expensestracker.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByUserIdAndExpenseCategoryId(Long userId, Long categoryId);
    // Now you can also use:
    // Page<Expense> findAll(Specification<Expense> spec, Pageable pageable);
}