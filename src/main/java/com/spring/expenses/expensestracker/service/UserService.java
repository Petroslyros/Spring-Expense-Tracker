package com.spring.expenses.expensestracker.service;

import com.spring.expenses.expensestracker.core.exceptions.AppObjectAlreadyExists;
import com.spring.expenses.expensestracker.core.exceptions.UserNotFoundException;
import com.spring.expenses.expensestracker.dto.UserInsertDTO;
import com.spring.expenses.expensestracker.dto.UserReadOnlyDTO;
import com.spring.expenses.expensestracker.mapper.Mapper;
import com.spring.expenses.expensestracker.model.User;
import com.spring.expenses.expensestracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {


    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserReadOnlyDTO registerUser(UserInsertDTO dto) throws AppObjectAlreadyExists {
        if(userRepository.findByEmail(dto.email()).isPresent()) {
            throw new AppObjectAlreadyExists("Email","Email already in use");
        }

        User user = mapper.mapDTOToUserEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        return mapper.mapUserEntityToReadOnlyDTO(savedUser);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }
}
