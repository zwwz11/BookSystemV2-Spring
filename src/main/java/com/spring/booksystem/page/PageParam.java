package com.spring.booksystem.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageParam {

    private int page = 1;
    private int amount = 10;

    private int start = 0;

    public void setPage(int page) {
        this.page = page;
        this.start = (page-1) * 10;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.start = (page-1) * 10;
    }
}
