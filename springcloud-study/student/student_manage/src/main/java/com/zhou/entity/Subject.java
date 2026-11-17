package com.zhou.entity;

import com.zhou.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;


@Data
public class Subject extends BaseEntity implements Serializable {
    private String name;
}
