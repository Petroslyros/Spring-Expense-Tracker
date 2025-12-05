package com.spring.expenses.expensestracker.core.specifications;

import com.spring.expenses.expensestracker.model.Expense;
import com.spring.expenses.expensestracker.model.ExpenseCategory;
import com.spring.expenses.expensestracker.model.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ExpenseSpecification {
    private ExpenseSpecification() {
    }

    public static Specification<Expense> expenseUserIdIs(Long userId) {
        return (root, query, builder) -> {
            if (userId == null) {
                return builder.isTrue(builder.literal(true));
            }
            Join<Expense, User> user = root.join("user");
            return builder.equal(user.get("id"), userId);
        };
    }

    public static Specification<Expense> expenseCategoryIdIs(Long categoryId) {
        return (root, query, builder) -> {
            if (categoryId == null) {
                return builder.isTrue(builder.literal(true));
            }
            Join<Expense, ExpenseCategory> category = root.join("expenseCategory");
            return builder.equal(category.get("id"), categoryId);
        };
    }

    public static Specification<Expense> expenseTitleLike(String title) {
        return (root, query, builder) -> {
            if (title == null || title.trim().isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(builder.upper(root.get("title")), "%" + title.toUpperCase() + "%");
        };
    }

    public static Specification<Expense> expenseNotDeleted() {
        return (root, query, builder) -> builder.equal(root.get("isDeleted"), false);
    }
}