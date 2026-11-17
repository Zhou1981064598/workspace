package com.example.SpringCloudStudy.microservice.rbac.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class EditRoleVo {
    @JsonAlias({"id"})
    private Long userId;

    @JsonAlias({"ids"})
    private List<Long> roleIds;
}
