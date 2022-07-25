package com.spring.booksystem.repository.user;


import com.spring.booksystem.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserMemoryRepository implements UserRepository {

    private static final Map<String, User> store = new HashMap<>();

    @Override
    public User findById(String id) {
        return store.get(id);
    }

    @Override
    public List<User> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public User save(User user) {
        user.setId(user.getId());
        store.put(user.getId(), user);
        return store.get(user.getId());
    }

    @Override
    public void update(String id, User updatedUser) {
        User targetUser = findById(id);
        targetUser.setPassword(updatedUser.getPassword());
        targetUser.setName(updatedUser.getName());
        targetUser.setAge(updatedUser.getAge());
        targetUser.setPhone(updatedUser.getPhone());
        targetUser.setEmail(updatedUser.getEmail());
        targetUser.setSex(updatedUser.getSex());
        targetUser.setAuth(updatedUser.getAuth());
    }

    @Override
    public void remove(String id) {
        store.remove(id);
    }
}
