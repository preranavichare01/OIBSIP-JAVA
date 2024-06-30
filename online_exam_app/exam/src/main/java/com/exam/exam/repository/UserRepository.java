package com.exam.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
