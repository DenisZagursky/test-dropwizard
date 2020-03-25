package com.wizard.web.pagination;

import java.util.List;

public class Page<T> {
    private List<T> content;
    private Long offset;
    private Long limit;
    private Long totalCount;
}
