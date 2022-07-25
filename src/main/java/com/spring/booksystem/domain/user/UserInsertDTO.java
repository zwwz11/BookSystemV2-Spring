package com.spring.booksystem.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@RequiredArgsConstructor
public class UserInsertDTO {

    @NotBlank(message = "아이디를 입력해주세요")
    @Length(min = 6, max = 15, message = "아이디는 최소 6자리부터 최대 15자리까지 입니다.")
    String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 8, max = 15, message = "비밀번호는 최소 8자리부터 최대 15자리까지 입니다.")
    String password;

    @NotBlank(message = "이름을 입력해주세요.")
    String name;

    @NotNull(message = "나이를 입력해주세요.")
    @Range(min = 0, max = 150, message = "나이가 올바르지 않습니다.")
    Integer age;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    String phone;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일이 올바르지 않습니다.")
    String email;

    @NotNull(message = "성별을 체크하세요.")
    UserSex sex;

}
