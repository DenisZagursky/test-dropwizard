package com.wizard.web.pagination;

import lombok.Data;
import org.jooq.SelectConditionStep;
import org.jooq.SelectLimitPercentAfterOffsetStep;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
public class Pageable {

    @NotNull
    @Min(0)
    @QueryParam("offset")
    @DefaultValue(value = "0")
    private Long offset;
    @QueryParam("limit")
    @DefaultValue(value = "10")
    private Long limit;
    private String param;
    private SortType sortType;


    public SelectLimitPercentAfterOffsetStep addPagination(SelectConditionStep query) {
        return query.offset(offset).limit(limit);

    }
}
