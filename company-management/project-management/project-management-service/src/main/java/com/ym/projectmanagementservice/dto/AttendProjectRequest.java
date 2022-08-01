package com.ym.projectmanagementservice.dto;

import lombok.Data;

import java.util.List;

/**
 * 远程调用使用请求类
 * 无论是加入某个项目或者加入某个经理的团队，都使用该类。接收的时候再细分
 */
@Data
public class AttendProjectRequest {

    private Long projectId;

    private List<Long> ids;

    private Long managerId;
}
