package com.ym.projectmanagementdao;

import com.ym.projectmanagementdao.entity.Project;
import com.ym.projectmanagementdao.entity.StaffProjectRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffProjectRelationDao extends JpaRepository<StaffProjectRelation, Long> {


}
