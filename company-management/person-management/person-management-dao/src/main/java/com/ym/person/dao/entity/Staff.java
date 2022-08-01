package com.ym.person.dao.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "staff")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_manager", nullable = false)
    private Integer isManager;

    // 为了简化操作，只记录部门id，部门模块可后续拓展
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "status", nullable = false)
    private Integer status;


//    @Column(name = "is_admin", nullable = false)
//    private Integer isAdmin;

}
