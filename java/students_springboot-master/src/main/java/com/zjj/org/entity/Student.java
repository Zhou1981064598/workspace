package com.zjj.org.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zjj.org.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 学生实体类
 * 关联t_student表
 * @author zjj
 * @date 2022-05-11
 */

@Entity    //映射到数据库表
@Data     //生成属性的get和set方法
public class Student extends BaseEntity {
    private String stuName;
    private String stuNo;
    private String idCard;
    private int sex;
    private String prov;
    private String city;
    private String address;
    private String stuTel;
    private String stuEmail;
    private String stuPeople;

}
