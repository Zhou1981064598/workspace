package com.zjj.org.entity;

import com.zjj.org.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zjj
 * @date 2022-05-25
 */
@Data
public class Subject extends BaseEntity implements Serializable {
    private String name;
}
