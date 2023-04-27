package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.enums.Role;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) return false;

        String userPosition = user.getPosition();

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateOfCreated(LocalDateTime.now());
        if(userPosition.equals("Project Manager") || userPosition.equals("Product Manager")) {
            user.getRoles().add(Role.ROLE_MANAGER);
        }
        else {
            user.getRoles().add(Role.ROLE_USER);
        }
        userRepository.save(user);

        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
