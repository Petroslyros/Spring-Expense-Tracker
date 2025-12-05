package com.spring.expenses.expensestracker.core.specifications;

import com.spring.expenses.expensestracker.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    private UserSpecification() {
    }

    public static Specification<User> usernameLike(String username) {
        return (root, query, builder) -> {
            if (username == null || username.trim().isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(builder.upper(root.get("username")), "%" + username.toUpperCase() + "%");
        };
    }

    public static Specification<User> emailLike(String email) {
        return (root, query, builder) -> {
            if (email == null || email.trim().isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(builder.upper(root.get("email")), "%" + email.toUpperCase() + "%");
        };
    }

    public static Specification<User> userNotDeleted() {
        return (root, query, builder) -> builder.equal(root.get("isDeleted"), false);
    }
}