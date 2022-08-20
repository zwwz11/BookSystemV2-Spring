package com.spring.booksystem.repository.user;

import com.spring.booksystem.domain.user.User;

import java.util.List;

public interface UserRepository {

    User findById(String id);
    List<User> findAll();
    User save(User user);
    void update(String id, User updatedUser);
    void remove(String id);
    int getUsersTotalCount();

}
