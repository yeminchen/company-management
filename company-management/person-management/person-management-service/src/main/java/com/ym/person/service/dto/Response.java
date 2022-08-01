package com.ym.person.service.dto;

import lombok.Data;

@Data
public class Response {

    // 1-成功 0-失败
    private Integer code;

    private String message;
}
