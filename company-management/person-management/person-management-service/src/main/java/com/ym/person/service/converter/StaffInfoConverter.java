package com.ym.person.service.converter;

import com.ym.person.dao.entity.Staff;
import com.ym.person.service.dto.StaffInfo;

public class StaffInfoConverter {

    public static StaffInfo convertToStaffInfo(Staff staff) {

        return StaffInfo.builder().id(staff.getId()).name(staff.getName()).isManager(staff.getIsManager()).build();
    }
}
