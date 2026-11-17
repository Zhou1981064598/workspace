package com.zhou.service;

import com.zhou.base.Result;
import com.zhou.entity.Building;
import com.zhou.entity.vo.BuildingVo;

import org.springframework.stereotype.Service;


@Service
public interface BuildingService {
    Result selectBulList(BuildingVo buildingVo);
    /**
     * 添加或者修改学生宿舍信息
     * @param building 宿舍信息实体类
     * @return
     */

    Result addAndEditBld(Building building );

    /**
     * 删除宿舍信息
     * @param id 主键
     * @return
     */
    Result deleteById(int id);


}
