package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.exceptions.AppObjectAlreadyExists;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import com.spring.expenses.expensestracker.dto.UserReadOnlyDTO;

public interface IUserService {
    UserReadOnlyDTO registerUser(UserInsertDTO dto) throws AppObjectAlreadyExists;
    UserReadOnlyDTO getUserById(Long id) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
}