package com.zhou.entity;

import com.zhou.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class Score extends BaseEntity implements Serializable {
    private Double score;
    private Integer subject;
    private String sno;
    private String syear;
}
