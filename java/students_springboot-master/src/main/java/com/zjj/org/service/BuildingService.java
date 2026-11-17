package com.zjj.org.service;

import com.zjj.org.base.Result;
import com.zjj.org.entity.Building;
import com.zjj.org.entity.Student;
import com.zjj.org.entity.vo.BuildingVo;
import org.springframework.stereotype.Service;

/**
 * @author zjj
 * @date 2022-05-17
 */
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
