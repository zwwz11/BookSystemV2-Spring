package com.spring.booksystem.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Book {

    Long id;

    String title;

    Integer price;

    BookType bookType;

    Boolean rent_YN;

    Integer rentCount;

    String description;

    String fileNM;

    String fileNM_UUID;
}
