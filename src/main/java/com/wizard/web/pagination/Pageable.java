package com.wizard.web.pagination;

import lombok.Data;
import org.jooq.SelectConditionStep;
import org.jooq.SelectLimitPercentAfterOffsetStep;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Pageable {

    @NotNull
    @Min(0)
    private Long offset;
    private Long limit;
    private String param;
    private SortType sortType;


    public SelectLimitPercentAfterOffsetStep addPagination(SelectConditionStep query) {
        return query.offset(offset).limit(limit);

    }
}
