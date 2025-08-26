package com.spring.expenses.expensestracker.mapper;

import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;
import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import com.spring.expenses.expensestracker.dto.UserReadOnlyDTO;
import com.spring.expenses.expensestracker.model.Expense;
import com.spring.expenses.expensestracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    public User mapDTOToUserEntity(UserInsertDTO dto) {
        return new User(null, dto.username(),dto.email(),dto.password(),dto.role(),null);
    }

    public UserReadOnlyDTO mapUserEntityToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(),user.getUsername(),user.getEmail(),user.getCreatedAt(),user.getUpdatedAt());
    }

    public ExpenseReadOnlyDTO expenseEntityToReadOnlyDTO(Expense expense) {
        return new ExpenseReadOnlyDTO(expense.getId(),expense.getTitle(),expense.getAmount(),expense.getDate());
    }

    public Expense expenseReadOnlyToEntity(ExpenseInsertDTO dto, User user) {
        return new Expense(dto.userId(),dto.title(),dto.amount(),dto.category(),dto.date(),user);
    }
}
