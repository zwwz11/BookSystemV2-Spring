package com.spring.booksystem.service.user;

import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.domain.user.UserAuth;
import com.spring.booksystem.domain.user.UserSex;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired private UserService userService;

    @Test
    @DisplayName("유저 중복 데이터 Insert")
    void userDuplicate() {
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("test");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        User userB = new User();
        userB.setId("User");
        userB.setPassword("test");
        userB.setName("test");
        userB.setSex(UserSex.MALE);
        userB.setAge(1);
        userB.setPhone("test");
        userB.setEmail("test");
        userB.setAuth(UserAuth.GENERAL);

        userService.join(userA);
        assertThrows(DuplicateKeyException.class, () -> userService.join(userB));
    }

    @Test
    @DisplayName("유저 등록 테스트")
    void saveUser() {
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("test");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        User joinedUser = userService.join(userA);
        assertThat(userA.getId()).isEqualTo(joinedUser.getId());
    }

    @Test
    @DisplayName("유저 업데이트 테스트")
    void updateUser() {
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("UserA");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        User userB = new User();
        userB.setId("User");
        userB.setPassword("test");
        userB.setName("UserB");
        userB.setSex(UserSex.MALE);
        userB.setAge(1);
        userB.setPhone("test");
        userB.setEmail("test");
        userB.setAuth(UserAuth.GENERAL);

        User joinedUser = userService.join(userA);
        userService.editUser(joinedUser.getId(), userB);
        User findUser = userService.findUser(joinedUser.getId());
        assertThat(findUser.getName()).isEqualTo(userB.getName());
    }
}