package com.zjj.org.entity;

import com.zjj.org.base.BaseEntity;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * @author zjj
 * @date 2022-05-23
 */
@Data
public class Score extends BaseEntity implements Serializable {
    private Double score;
    private Integer subject;
    private String sno;
    private String syear;
}
