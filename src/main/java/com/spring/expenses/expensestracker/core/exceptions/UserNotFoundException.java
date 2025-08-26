package com.spring.expenses.expensestracker.core.exceptions;

public class UserNotFoundException extends AppGenericException{

    public UserNotFoundException(Long userId) {
        super("USER_NOT>FOUND", "User with ID " + userId + "not found.");
    }
}
