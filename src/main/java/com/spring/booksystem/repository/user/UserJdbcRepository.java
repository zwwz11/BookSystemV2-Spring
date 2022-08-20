package com.spring.booksystem.repository.user;

import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.domain.user.UserAuth;
import com.spring.booksystem.domain.user.UserSex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserJdbcRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(String id) {
        List<User> findUser = jdbcTemplate.query("SELECT * FROM TB_COM_USER WHERE USER_ID = ?", userRowMapper(), id);
        return findUser.stream().findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<User> findUsers = jdbcTemplate.query("SELECT * FROM TB_COM_USER", userRowMapper());
        return findUsers;
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("TB_COM_USER");

        Map<String, Object> param = new HashMap<>();
        param.put("USER_ID", user.getId());
        param.put("PASSWORD", user.getPassword());
        param.put("AUTH_COM_CD", UserAuth.GENERAL);
        param.put("NAME", user.getName());
        param.put("AGE", user.getAge());
        param.put("PHONE", user.getPhone());
        param.put("EMAIL", user.getEmail());
        param.put("SEX_COM_CD", user.getSex());
        param.put("CREATE_DT", LocalDateTime.now());
        jdbcInsert.execute(param);
        return findById(user.getId());
    }

    @Override
    public void update(String id, User updatedUser) {
        jdbcTemplate.update("UPDATE TB_COM_USER " +
                                "SET PASSWORD = ?" +
                                "  , AUTH_COM_CD = ?" +
                                "  , NAME = ?" +
                                "  , AGE = ?" +
                                "  , PHONE = ?" +
                                "  , EMAIL = ?" +
                                "  , SEX_COM_CD = ?" +
                                "WHERE USER_ID = ?" , updatedUser.getPassword()
                                                    , updatedUser.getAuth().toString()
                                                    , updatedUser.getName()
                                                    , updatedUser.getAge()
                                                    , updatedUser.getPhone()
                                                    , updatedUser.getEmail()
                                                    , updatedUser.getSex().toString()
                                                    , id);
    }

    @Override
    public void remove(String id) {
        jdbcTemplate.update("DELETE FROM TB_COM_USER WHERE USER_ID = ?", id);
    }

    @Override
    public int getUsersTotalCount() {
        List<Integer> totalCount = jdbcTemplate.query("SELECT COUNT(USER_ID) AS TOTAL FROM TB_COM_USER", bookTotalCountRowMapper());
        return totalCount.get(0);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getString("USER_ID"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setAuth(UserAuth.valueOf(rs.getString("AUTH_COM_CD")));
            user.setName(rs.getString("NAME"));
            user.setAge(rs.getInt("AGE"));
            user.setPhone(rs.getString("PHONE"));
            user.setEmail(rs.getString("EMAIL"));
            user.setSex(UserSex.valueOf(rs.getString("SEX_COM_CD")));
            return user;
        };
    }

    private RowMapper<Integer> bookTotalCountRowMapper() {

        return (rs, rowNum) -> {
            return rs.getInt("TOTAL");
        };
    }

    // 람다 사용전 문법
    private RowMapper<Integer> test() {
        return new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                int totalCount = rs.getInt("TOTAL");
                return totalCount;
            }
        };
    }
}
