package com.ym.projectmanagementdao;

import com.ym.projectmanagementdao.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends JpaRepository<Project, Long> {

    List<Project> findAllByManagerId(Long id);

    @Modifying
    @Query("update Project a set a.status = :status where a.id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    @Modifying
    @Query("update Project a set a.managerId = :manager_id where a.id = :id")
    int updateManagerId(@Param("id") Long id, @Param("manager_id") Long managerId);
}
