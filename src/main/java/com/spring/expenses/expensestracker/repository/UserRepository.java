package com.spring.expenses.expensestracker.repository;

import com.spring.expenses.expensestracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    // Page<User> findAll(Specification<User> spec, Pageable pageable);
}