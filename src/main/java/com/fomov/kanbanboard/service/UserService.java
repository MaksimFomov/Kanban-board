package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.enums.Role;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) return false;

        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        return true;
    }
}
