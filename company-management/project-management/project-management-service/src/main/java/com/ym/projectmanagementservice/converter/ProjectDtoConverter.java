package com.ym.projectmanagementservice.converter;

import com.ym.projectmanagementdao.entity.Project;
import com.ym.projectmanagementservice.dto.ProjectDto;

public class ProjectDtoConverter {

    public static ProjectDto convertToProjectDto(Project project) {

        return ProjectDto.builder().id(project.getId()).name(project.getName()).
                description(project.getDescription()).
                endDate(project.getEndDate()).status(project.getStatus()).
                managerId(project.getManagerId()).build();
    }
}
