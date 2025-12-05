package com.spring.expenses.expensestracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense_categories")
public class ExpenseCategory extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Navigation property
    @OneToMany(mappedBy = "expenseCategory", cascade = CascadeType.PERSIST)
    private Set<Expense> expenses = new HashSet<>();
}