package com.exam.exam.service;

import com.exam.exam.model.User;

public interface UserService {
    User findByUsername(String username);

    User save(User user);
}
