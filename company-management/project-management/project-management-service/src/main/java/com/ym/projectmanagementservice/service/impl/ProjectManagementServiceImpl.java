package com.ym.projectmanagementservice.service.impl;

import com.ym.projectmanagementdao.ProjectDao;
import com.ym.projectmanagementdao.StaffProjectRelationDao;
import com.ym.projectmanagementdao.entity.Project;
import com.ym.projectmanagementdao.entity.StaffProjectRelation;
import com.ym.projectmanagementservice.converter.ProjectDtoConverter;
import com.ym.projectmanagementservice.dto.ProjectDto;
import com.ym.projectmanagementservice.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private StaffProjectRelationDao staffProjectRelationDao;

    @Override
    public List<ProjectDto> findAllProjectByManagerId(Long id) {
        List<Project> list = projectDao.findAllByManagerId(id);
        return list.stream().map(ProjectDtoConverter::convertToProjectDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto createNewProject(ProjectDto projectDto) {
        // 后续还需要加上关键字段的判断
        if (projectDto == null) {
            throw new UnsupportedOperationException("no param");
        }
        Project project = Project.builder().name(projectDto.getName()).description(projectDto.getDescription())
                .status(1).endDate(99999999).build();

        return ProjectDtoConverter.convertToProjectDto(projectDao.save(project));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProject(Long projectId) {
        int rows = projectDao.updateStatus(projectId, 0);
        if (rows == 0) {
            throw new UnsupportedOperationException("no such project");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignProject(Long projectId, Long managerId) {
        int rows = projectDao.updateManagerId(projectId, managerId);
        if (rows == 0) {
            throw new UnsupportedOperationException("no such project");
        }
    }

    @Override
    public void addMember(Long projectId, List<Long> staffIds) {
        // StaffProjectRealation表插入新数据
        for (Long staffId : staffIds) {
            StaffProjectRelation staffProjectRelation = new StaffProjectRelation();
            staffProjectRelation.setProjectId(projectId);
            staffProjectRelation.setStaffId(staffId);
            staffProjectRelationDao.save(staffProjectRelation);
        }
    }

    @Override
    public void joinTeam(Long managerId, List<Long> staffIds) {
        // 查出所有所属project
        List<Project> allByManagerId = projectDao.findAllByManagerId(managerId);
        for (Project project : allByManagerId) {
            for (Long staffId : staffIds) {
                StaffProjectRelation staffProjectRelation = new StaffProjectRelation();
                staffProjectRelation.setProjectId(project.getId());
                staffProjectRelation.setStaffId(staffId);
                staffProjectRelationDao.save(staffProjectRelation);
            }
        }
    }

}
