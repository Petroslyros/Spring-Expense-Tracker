package com.spring.expenses.expensestracker.core.exceptions;

public class ExpenseNotFoundException extends AppGenericException{

    public ExpenseNotFoundException(Long expenseId) {
        super("EXPENSE_NOT_FOUND", "EXPENSE with ID" + expenseId + "not found");
    }
}
