package com.wizard.web.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {
    private List content;
    private Long offset;
    private Integer limit;
    private Long totalCount;


    public static Page of(List content, Long totalCount, Pageable pageable) {
        return Page.builder()
                .content(content)
                .totalCount(totalCount)
                .offset(pageable.getOffset())
                .limit(content.size())
                .build();
    }
}
