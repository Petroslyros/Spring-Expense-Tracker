package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.exceptions.AppObjectAlreadyExists;
import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import com.spring.expenses.expensestracker.dto.UserReadOnlyDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    public UserReadOnlyDTO registerUser(UserInsertDTO dto) throws AppObjectAlreadyExists;
}
