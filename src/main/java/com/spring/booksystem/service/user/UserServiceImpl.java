package com.spring.booksystem.service.user;

import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.repository.user.UserMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMemoryRepository userMemoryRepository;

    @Override
    public User findUser(String id) {
        return userMemoryRepository.findById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userMemoryRepository.findAll();
    }

    @Override
    public User join(User user) {
        return userMemoryRepository.save(user);
    }

    @Override
    public void editUser(String id, User user) {
        userMemoryRepository.update(id, user);
    }

    @Override
    public void deleteUser(String id) {
        userMemoryRepository.remove(id);
    }
}
