package com.spring.booksystem.service.user;

import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User findUser(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User join(User user) {
        return userRepository.save(user);
    }

    @Override
    public void editUser(String id, User user) {
        userRepository.update(id, user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.remove(id);
    }
}
