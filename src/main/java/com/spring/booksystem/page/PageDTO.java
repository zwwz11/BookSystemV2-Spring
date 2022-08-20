package com.spring.booksystem.page;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class PageDTO {

    private int endPage;
    private final int startPage;
    private final int realEnd;
    private final int totalCount;
    private final int currentPage;

    private final boolean prev;
    private final boolean next;

    public PageDTO(PageParam pageParam, int totalCount) {
        this.totalCount = totalCount;

        currentPage = pageParam.getPage();
        int amount = pageParam.getAmount();

        this.endPage = (int)(Math.ceil(currentPage * 0.1)) * 10;
        this.startPage = endPage - 9;
        this.realEnd = (int) Math.ceil(totalCount / (double)amount);

        if (realEnd < endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage - 10 >= 1;
        this.next = this.endPage + 1 <= realEnd;
    }
}
