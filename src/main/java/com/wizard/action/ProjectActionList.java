package com.wizard.action;

import com.wizard.web.pagination.Page;
import com.wizard.web.pagination.Pageable;
import com.wizard.web.request.ProjectFilterRequest;
import com.wizard.web.response.ShortProjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.jooq.codegen.maven.example.tables.Project.PROJECT;

@Slf4j
public class ProjectActionList {

    public Page getProjectsByFilter(ProjectFilterRequest filter, Pageable pageable, DSLContext db) {
        var defaultSql = db.selectFrom(PROJECT)
                .where(PROJECT.DELETED.isFalse());
        var condition = PROJECT.DELETED.isFalse();
        if (filter.getCreatedOn() != null)
            condition = condition.and(PROJECT.CREATED_ON.ge(filter.getCreatedOn()));
        if (filter.getDeadline() != null)
            condition = condition.and(PROJECT.DEADLINE.le(filter.getDeadline()));
        if (isNotBlank(filter.getName()))
            condition = condition.and(PROJECT.NAME.equalIgnoreCase(filter.getName()));
        log.info(defaultSql.getSQL());
        var totalCount = db.selectCount().from(PROJECT).where(condition).fetchSingleInto(Long.class);
        var target = pageable.addPagination(defaultSql).fetchInto(ShortProjectResponse.class);
        return Page.of(target, totalCount, pageable);
    }
}
