package com.ym.projectmanagementdao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "staff_project_relation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffProjectRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;
}
