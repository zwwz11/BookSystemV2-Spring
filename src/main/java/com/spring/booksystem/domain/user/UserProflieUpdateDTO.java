package com.spring.booksystem.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class UserProflieUpdateDTO {
    String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 8, max = 15, message = "비밀번호는 최소 8자리부터 최대 15자리까지 입니다.")
    String password;

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
