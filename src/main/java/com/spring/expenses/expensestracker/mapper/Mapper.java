package com.spring.expenses.expensestracker.mapper;

import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import com.spring.expenses.expensestracker.dto.UserReadOnlyDTO;
import com.spring.expenses.expensestracker.dto.ExpenseInsertDTO;
import com.spring.expenses.expensestracker.dto.ExpenseReadOnlyDTO;
import com.spring.expenses.expensestracker.model.User;
import com.spring.expenses.expensestracker.model.Expense;
import com.spring.expenses.expensestracker.model.ExpenseCategory;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    // User Mappings
    public static User mapUserInsertDTOToUser(UserInsertDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setUserRole(dto.userRole());
        user.setIsDeleted(false);
        return user;
    }

    public static UserReadOnlyDTO mapUserToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getInsertedAt(),
                user.getModifiedAt()
        );
    }

    // Expense Mappings
    public static Expense mapExpenseInsertDTOToExpense(ExpenseInsertDTO dto, User user, ExpenseCategory category) {
        Expense expense = new Expense();
        expense.setTitle(dto.title());
        expense.setAmount(dto.amount());
        expense.setDate(dto.date());
        expense.setExpenseCategory(category);
        expense.setUser(user);
        return expense;
    }

    public ExpenseReadOnlyDTO mapExpenseToExpenseReadOnlyDTO(Expense expense) {
        String categoryName = expense.getExpenseCategory() != null ?
                expense.getExpenseCategory().getName() : "Uncategorized";

        return new ExpenseReadOnlyDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDate(),
                categoryName,
                expense.getInsertedAt(),
                expense.getModifiedAt()
        );
    }
}