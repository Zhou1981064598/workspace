package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseInfo对象", description = "")
public class CourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String courseDate;

    private String courseName;

    private Integer courseTeacherId;

    private String courseSignTime;

    private String courseOutTime;

    private String courseClassroom;

    private String courseNote;

    private Date lastUpgradeTime;

    private Integer lastUpgradePerson;

    private Date createTime;

    private Integer createPerson;


}
