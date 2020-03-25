package com.wizard.web.request;

import com.wizard.domain.ProjectStatus;
import lombok.Data;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProjectFilterRequest {

    @QueryParam("name")
    private String name;
    @QueryParam("start")
    private LocalDate start;
    @QueryParam("deadline")
    private LocalDateTime deadline;
    @QueryParam("estimate")
    private Float estimate;
    @QueryParam("status")
    private ProjectStatus status;
    @QueryParam("createdOn")
    private LocalDateTime createdOn;
}
