package com.ym.person.service.service;

import com.ym.person.service.dto.AttendProjectRequest;
import com.ym.person.service.dto.StaffInfo;
import com.ym.person.service.dto.Response;

import java.util.List;

public interface PersonManagementService {

    /**
     * 添加新员工
     * @param staffInfo
     * @return
     */
    StaffInfo addNewStaff(StaffInfo staffInfo);

    /**
     * 根据姓名查找相关员工 (若name为空字符串，则查询全部）
     */
    List<StaffInfo> findStaffByName(String name);

    /**
     * 员工离职
     */
    void deleteStaff(Long id);

    /**
     * 员工晋升
     */
    void updateIsManager(Long id);

    /**
     * 员工加入项目
     */
    void attendProject(AttendProjectRequest attendProjectRequest);

    /**
     * 员工加入项目组（加入某位经理的所有项目中）
     */
    void joinTeam(AttendProjectRequest attendProjectRequest);
}
