package com.spring.booksystem.domain.book;

public enum BookType {
    NOVEL("소설"),
    ESSAY("에세이"),
    EDUCATION("교육");

    private final String value;

    BookType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
