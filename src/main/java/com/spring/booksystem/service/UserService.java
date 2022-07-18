package com.spring.booksystem.service;

import com.spring.booksystem.domain.user.User;

import java.util.List;

public interface UserService {
    User findUser(Long id);
    List<User> findAllUser();
    User join(User user);
    void editUser(Long id, User user);
    void deleteUser(Long id);
}
