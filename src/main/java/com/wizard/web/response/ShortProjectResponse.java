package com.wizard.web.response;

import com.wizard.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortProjectResponse {
    private Long id;
    private String name;

    public static ShortProjectResponse from(Project project) {
        return ShortProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }
}
