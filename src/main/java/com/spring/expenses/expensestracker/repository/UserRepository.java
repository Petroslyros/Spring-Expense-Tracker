package com.spring.expenses.expensestracker.repository;

import com.spring.expenses.expensestracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
