package com.spring.booksystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @NotNull
    Long id;

    @NotBlank(message = "이름을 입력해주세요.")
    String name;

    @NotNull(message = "나이를 입력해주세요.")
    @Positive(message = "나이가 올바르지 않습니다.")
    int age;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    String phone;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일이 올바르지 않습니다.")
    String email;

    @NotNull
    UserSex sex;


}
