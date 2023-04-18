package com.fomov.kanbanboard.repository;

import com.fomov.kanbanboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
