package com.ym.person.service.controller;

import com.ym.person.service.dto.AttendProjectRequest;
import com.ym.person.service.dto.StaffInfo;
import com.ym.person.service.service.PersonManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/person")
public class PersonManagementController {

    @Autowired
    private PersonManagementService personManagementService;

    @PostMapping("/add")
    public StaffInfo addNewStaff(@Valid @RequestBody StaffInfo request) {
        log.info("Create new staff: data={}", request);
        return personManagementService.addNewStaff(request);
    }

    @GetMapping("/list")
    public List<StaffInfo> findAll() {
        log.info("search all staff");
        return personManagementService.findStaffByName("");
    }

    @GetMapping("/list/byname")
    public List<StaffInfo> findAllByName(@RequestParam String name) {
        log.info("search all staff by name");
        if ("".equals(name)) {
            throw new UnsupportedOperationException("please enter effective name");
        }
        return personManagementService.findStaffByName(name);
    }

    @PutMapping("/off")
    public void staffOff(@Valid @RequestParam Long id) {
        log.info("staff leaves");
        personManagementService.deleteStaff(id);
    }

    @PutMapping("/promote")
    public void promoteStaff(@Valid @RequestParam Long id) {
        log.info("staff promotion");
        personManagementService.updateIsManager(id);
    }

    @PutMapping("/attend")
    public void attendProject(@Valid @RequestBody AttendProjectRequest attendProjectRequest) {
        log.info("attend a project");
        personManagementService.attendProject(attendProjectRequest);
    }

    @PutMapping("/attendAll")
    public void joinTeam(@Valid @RequestBody AttendProjectRequest attendProjectRequest) {
        log.info("attend a team");
        personManagementService.joinTeam(attendProjectRequest);
    }
}
