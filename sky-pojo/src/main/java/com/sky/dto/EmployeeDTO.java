package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@Data
@ApiModel(description = "员工登录时传递的数据模型")
public class EmployeeDTO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
