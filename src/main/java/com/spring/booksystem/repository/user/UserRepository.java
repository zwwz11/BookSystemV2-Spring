package com.spring.booksystem.repository.user;

import com.spring.booksystem.domain.user.User;

import java.util.List;

public interface UserRepository {

    User findById(Long id);
    List<User> findAll();
    User save(User user);
    void update(Long id, User updatedUser);
    void remove(Long id);

}
