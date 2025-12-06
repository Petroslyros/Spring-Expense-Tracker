package com.spring.expenses.expensestracker.controller;


import com.spring.expenses.expensestracker.core.exceptions.AppObjectAlreadyExists;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import com.spring.expenses.expensestracker.dto.UserReadOnlyDTO;
import com.spring.expenses.expensestracker.mapper.Mapper;
import com.spring.expenses.expensestracker.model.User;
import com.spring.expenses.expensestracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserReadOnlyDTO> register(@RequestBody UserInsertDTO dto) throws AppObjectAlreadyExists {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    // getUserWithID
    //updateUser
    //getPaginatedUsers




}
