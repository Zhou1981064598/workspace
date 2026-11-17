package com.example.SpringCloudStudy.microservice.rbac.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class RoleMenuVo {
    @JsonAlias({"id"})
    private Long roleId;

    @JsonAlias({"ids"})
    private List<Long> menuIds;
}
