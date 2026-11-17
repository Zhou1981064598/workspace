package com.zhou.entity;

import com.zhou.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;


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
