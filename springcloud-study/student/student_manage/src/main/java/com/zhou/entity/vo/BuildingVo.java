package com.zhou.entity.vo;

import com.zhou.base.BasePage;
import lombok.Data;


@Data
public class BuildingVo  extends BasePage {
    private String floor;
    private String roomNum;
}
