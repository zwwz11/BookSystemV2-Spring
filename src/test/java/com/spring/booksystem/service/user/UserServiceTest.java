package com.spring.booksystem.service.user;

import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.domain.user.UserAuth;
import com.spring.booksystem.domain.user.UserSex;
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
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("유저 중복ID 등록 테스트")
    void userDuplicate() {
        // given
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

        // when
        userService.join(userA);

        // then
        assertThrows(DuplicateKeyException.class, () -> userService.join(userB));
    }

    @Test
    @DisplayName("유저 등록 테스트")
    void saveUser() {
        // given
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("test");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        // when
        User joinedUser = userService.join(userA);

        // then
        assertThat(userA.getId()).isEqualTo(joinedUser.getId());
    }

    @Test
    @DisplayName("유저 업데이트 테스트")
    void updateUser() {
        // given
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

        // when
        User joinedUser = userService.join(userA);
        userService.editUser(joinedUser.getId(), userB);
        User findUser = userService.findUser(joinedUser.getId());

        // then
        assertThat(findUser.getName()).isEqualTo(userB.getName());
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void deleteUser() {
        // given
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("UserA");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        // when
        User joinedUser = userService.join(userA);
        userService.deleteUser(joinedUser.getId());
        User findUser = userService.findUser(joinedUser.getId());

        //then
        assertThat(findUser).isEqualTo(null);
    }

    @Test
    @DisplayName("똑같은 유저 두 번 삭제 테스트")
    void deleteUserDuplicate() {
        // given
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("UserA");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        // when
        User joinedUser = userService.join(userA);
        userService.deleteUser(joinedUser.getId());
        userService.deleteUser(joinedUser.getId());
        User findUser = userService.findUser(joinedUser.getId());

        //then
        assertThat(findUser).isEqualTo(null);
    }

    @Test
    @DisplayName("유저 찾기 테스트")
    void findUser() {
        // given
        User userA = new User();
        userA.setId("User");
        userA.setPassword("test");
        userA.setName("UserA");
        userA.setSex(UserSex.MALE);
        userA.setAge(1);
        userA.setPhone("test");
        userA.setEmail("test");
        userA.setAuth(UserAuth.GENERAL);

        // when
        User joinedUser = userService.join(userA);
        User findUser = userService.findUser(joinedUser.getId());

        //then
        assertThat(findUser.getId()).isEqualTo(joinedUser.getId());
    }
}