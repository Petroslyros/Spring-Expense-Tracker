
package com.spring.expenses.expensestracker.repository;

import com.spring.expenses.expensestracker.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}