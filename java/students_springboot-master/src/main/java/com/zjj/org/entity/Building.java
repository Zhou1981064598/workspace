package com.zjj.org.entity;

import com.zjj.org.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author zjj
 * @date 2022-05-17
 */
@Entity
@Data
public class Building extends BaseEntity implements Serializable {
    private String buildingNum;
    private String floor;
    private String roomNum;
    private int roomType;
    private int isBathroom;
    private int isAir;
}
