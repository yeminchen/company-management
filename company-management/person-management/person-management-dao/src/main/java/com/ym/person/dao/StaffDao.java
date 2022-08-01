package com.ym.person.dao;

import com.ym.person.dao.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface StaffDao extends JpaRepository<Staff, Long> {

    @Modifying
    @Query("update Staff a set a.isManager = 1 where a.id = :id")
    int promotionStaff(@Param("id") Long id);

    @Modifying
    @Query("update Staff a set a.status = 0 where a.id = :id")
    int makeStaffOff(@Param("id") Long id);

    // IN查询 + 分页支持的语法
    Page<Staff> findAllByIdIn(List<Long> Id, Pageable page);

    Page<Staff> findAllByName(String name, Pageable page);

    List<Staff> findAllByName(String name);
}
