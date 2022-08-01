package com.ym.person.service.service.impl;

import com.ym.person.dao.StaffDao;
import com.ym.person.dao.entity.Staff;
import com.ym.person.service.dto.AttendProjectRequest;
import com.ym.person.service.dto.StaffInfo;
import com.ym.person.service.converter.StaffInfoConverter;
import com.ym.person.service.dto.Response;
import com.ym.person.service.service.PersonManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonManagementServiceImpl implements PersonManagementService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private WebClient.Builder weClientBuilder;

    @Override
    public StaffInfo addNewStaff(StaffInfo staffInfo) {

        if (staffInfo == null) {
            log.error("empty request");
            throw new UnsupportedOperationException("empty request");
        }

        // 部门暂时都指定1
        Staff staff = Staff.builder()
                .name(staffInfo.getName()).isManager(staffInfo.getIsManager()).status(1).departmentId(1).build();
        staff = staffDao.save(staff);

        return StaffInfoConverter.convertToStaffInfo(staff);

    }

    @Override
    public List<StaffInfo> findStaffByName(String name) {

        List<Staff> list;
        // 判断name是否是空字符串
        if ("".equals(name)) {
             list = staffDao.findAll();
        } else {
            list = staffDao.findAllByName(name);
        }
        return list.stream().map(StaffInfoConverter::convertToStaffInfo).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStaff(Long id) {
        int rows = staffDao.makeStaffOff(id);
        if (rows == 0) {
            throw new UnsupportedOperationException("no such staff, plz check it out");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIsManager(Long id) {
        int rows = staffDao.promotionStaff(id);
        if (rows == 0) {
            throw new UnsupportedOperationException("no such staff, plz check it out");
        }
    }

    @Override
    public void attendProject(AttendProjectRequest attendProjectRequest) {
        if (attendProjectRequest.getIds().size() <= 0 ) {
            throw new UnsupportedOperationException("please give me some staff ids");
        }
        // 远程调用
        Response response = weClientBuilder.build()
                .post().uri("http://project-management/project/attend")
                .bodyValue(attendProjectRequest).retrieve().bodyToMono(Response.class).block();
        if (response != null && response.getCode() == 0) {
            log.error("attend failure");
            throw new UnsupportedOperationException("attend failure");
        }
    }

    @Override
    public void joinTeam(AttendProjectRequest attendProjectRequest) {
        if (attendProjectRequest.getIds().size() <= 0 || attendProjectRequest.getManagerId() == null) {
            throw new UnsupportedOperationException("param error");
        }
        // 远程调用
        Response response = weClientBuilder.build()
                .post().uri("http://project-management/project/attendAll")
                .bodyValue(attendProjectRequest).retrieve().bodyToMono(Response.class).block();
        if (response != null && response.getCode() == 0) {
            log.error("attend failure");
            throw new UnsupportedOperationException("attend failure");
        }
    }
}
