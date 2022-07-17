package com.spring.booksystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@RequiredArgsConstructor
public class UserUpdateDTO {

    @NotNull(message = "ID는 필수입니다.")
    Long id;

    @NotBlank(message = "이름을 입력해주세요.")
    String name;

    @NotNull(message = "나이를 입력해주세요.")
    @Range(min = 0, max = 150, message = "나이가 올바르지 않습니다.")
    Integer age;

    String phone;

    String email;

    @NotNull(message = "성별을 체크하세요")
    UserSex sex;
}
