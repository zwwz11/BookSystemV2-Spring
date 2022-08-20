package com.spring.booksystem.service.user;

import com.spring.booksystem.domain.user.User;

import java.util.List;

public interface UserService {
    User findUser(String id);
    List<User> findAllUser();
    User join(User user);
    void editUser(String id, User user);
    void deleteUser(String id);
    int getUsersTotalCount();
}
