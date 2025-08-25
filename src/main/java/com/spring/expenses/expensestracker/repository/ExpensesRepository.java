package com.spring.expenses.expensestracker.repository;

import com.spring.expenses.expensestracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense,Long> {
}
