package com.spring.expenses.expensestracker.repository;

import com.spring.expenses.expensestracker.core.enums.Category;
import com.spring.expenses.expensestracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByUserId(Long userId);

    @Query("SELECT r FROM Expense r WHERE r.user.id = :userId AND r.category = :category")
    List<Expense> findByUserIdAndCategory(Long userId, Category category);
}
