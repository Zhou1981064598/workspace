package com.example.SpringCloudStudy.microservice.rbac.vo;

import lombok.Data;

@Data
public class ChangePasswordVo {
    private String oldPassword;
    private String newPassword;
    private String fixPassword;
}
