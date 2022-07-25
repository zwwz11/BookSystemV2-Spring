package com.spring.booksystem.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    String id;
    String password;
    UserAuth auth;
    String name;
    Integer age;
    String phone;
    String email;
    UserSex sex;

}
