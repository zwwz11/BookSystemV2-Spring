package com.spring.booksystem.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@RequiredArgsConstructor
public class BookUpdateDTO {

    @NotNull(message = "ID는 필수입니다.")
    Long id;

    @NotBlank(message = "책 제목을 입력해주세요")
    String title;

    @NotNull(message = "가격을 입력해주세요")
    @Positive(message = "가격이 잘못되었습니다.")
    Integer price;

    @NotNull(message = "책 타입을 체크해주세요")
    BookType bookType;

    String description;
    String fileNM;
    String fileNM_UUID;
}
