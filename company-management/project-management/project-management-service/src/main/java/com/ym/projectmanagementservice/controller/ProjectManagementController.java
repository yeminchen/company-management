package com.ym.projectmanagementservice.controller;

import com.ym.projectmanagementservice.dto.AttendProjectRequest;
import com.ym.projectmanagementservice.dto.ProjectDto;
import com.ym.projectmanagementservice.service.ProjectManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/project")
public class ProjectManagementController {

    @Autowired
    private ProjectManagementService projectManagementService;

    @GetMapping("/list/bymanager")
    public List<ProjectDto> findAllByManagerId(@RequestParam Long managerId) {
        log.info("find all by manager : data={}", managerId);
        return projectManagementService.findAllProjectByManagerId(managerId);
    }

    @PostMapping("/add")
    public ProjectDto createNewProject(@Valid @RequestBody ProjectDto projectDto) {
        log.info("add new project : data={}", projectDto);
        return projectManagementService.createNewProject(projectDto);
    }

    @PutMapping("/delete")
    public void deleteProject(@RequestParam Long projectId) {
        log.info("delete project : data={}", projectId);
        projectManagementService.deleteProject(projectId);
    }

    @PutMapping("/assign")
    public void assignProject(@RequestParam Long projectId, @RequestParam Long managerId) {
        log.info("delete project : project id ={}, manager id = {}", projectId, managerId);
        projectManagementService.assignProject(projectId, managerId);
    }

    @PostMapping("/attend")
    public void attend(@Valid @RequestBody AttendProjectRequest attendProjectRequest) {
        log.info("delete project ");
        if (attendProjectRequest.getProjectId() == null || attendProjectRequest.getIds() == null) {
            throw new UnsupportedOperationException("param lost");
        }
        projectManagementService.addMember(attendProjectRequest.getProjectId(), attendProjectRequest.getIds());
    }

    @PostMapping("/attendAll")
    public void joinTeam(@Valid @RequestBody AttendProjectRequest attendProjectRequest) {
        if (attendProjectRequest.getManagerId() == null || attendProjectRequest.getIds() == null) {
            throw new UnsupportedOperationException("param lost");
        }
        projectManagementService.joinTeam(attendProjectRequest.getManagerId(), attendProjectRequest.getIds());
    }
}
