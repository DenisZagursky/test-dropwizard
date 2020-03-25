package com.wizard.domain;

import com.wizard.web.request.ProjectRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "start")
    private LocalDate start;
    @Column(name = "deadline")
    private LocalDateTime deadline;
    @Column(name = "estimate")
    private Integer estimate;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @Column(name = "last_changed_by")
    private String lastChangedBy;
    @Column(name = "deleted")
    private boolean deleted;

    public static Project create(ProjectRequest source, String name) {
        return Project.builder()
                .start(source.getStart())
                .name(source.getName())
                .createdBy(name)
                .estimate(source.getEstimate())
                .status(ProjectStatus.PLANNED)
                .lastUpdate(LocalDateTime.now())
                .createdOn(LocalDateTime.now())
                .lastChangedBy(name)
                .deleted(false)
                .build();
    }
}
