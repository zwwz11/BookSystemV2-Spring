package com.spring.booksystem.repository.user;

import com.spring.booksystem.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserJdbcRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void update(String id, User updatedUser) {

    }

    @Override
    public void remove(String id) {

    }
}
