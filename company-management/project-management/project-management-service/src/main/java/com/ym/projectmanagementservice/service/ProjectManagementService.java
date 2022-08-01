package com.ym.projectmanagementservice.service;

import com.ym.projectmanagementdao.entity.Project;
import com.ym.projectmanagementservice.dto.ProjectDto;

import java.util.List;

public interface ProjectManagementService {

    List<ProjectDto> findAllProjectByManagerId(Long id);

    ProjectDto createNewProject(ProjectDto projectDto);

    /**
     * 项目下线
     * @param projectId
     */
    void deleteProject(Long projectId);

    /**
     * 分配新项目给新的负责人
     */
    void assignProject(Long projectId, Long managerId);

    /**
     * 项目添加参与者
     * @param staffIds
     */
    void addMember(Long projectId, List<Long> staffIds);

    /**
     * 加入经理所在所有项目
     * @param staffIds
     */
    void joinTeam(Long managerId, List<Long> staffIds);
}
