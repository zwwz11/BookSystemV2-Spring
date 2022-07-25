package com.spring.booksystem.domain.user;

public enum UserAuth {
    ADMIN("관리자"),
    GENERAL("일반사용자");

    private final String value;

    UserAuth(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
