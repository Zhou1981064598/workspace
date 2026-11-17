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
 * VIEW
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StudentHomeworkView对象", description = "VIEW")
public class StudentHomeworkView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer homeworkId;

    private Integer studentId;

    private Date createTime;

    private Integer courseId;

    private String courseName;

    private String homeworkTitle;

    private Integer homeworkType;

    private Integer teacherId;

    private String teacher;

    private Date homeworkExpiry;

    private Integer homeworkStatus;

    private Date lastUpgradeTime;

    private String homeworkScore;


}
