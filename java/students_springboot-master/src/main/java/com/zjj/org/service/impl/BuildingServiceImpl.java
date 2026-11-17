package com.zjj.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjj.org.base.Result;
import com.zjj.org.base.ResultCode;
import com.zjj.org.entity.Building;
import com.zjj.org.entity.vo.BuildingVo;
import com.zjj.org.mapper.BuildingMapper;
import com.zjj.org.service.BuildingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjj
 * @date 2022-05-17
 */
@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Override
    public Result selectBulList(BuildingVo buildingVo) {
        //        分页
        PageHelper.startPage(buildingVo.getPage(),buildingVo.getLimit());
        //        查询
        QueryWrapper<Building> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotEmpty(buildingVo.getFloor())){
            wrapper.eq("floor",buildingVo.getFloor());
        }
        if (StringUtils.isNotEmpty(buildingVo.getRoomNum())){
            wrapper.eq("room_num",buildingVo.getRoomNum());
        }

        List<Building> userList= buildingMapper.selectList(wrapper);
        //        获取分页数据
        PageInfo<Building> pageInfo=new PageInfo<>(userList);
        return Result.success(pageInfo.getList(),(int)pageInfo.getTotal());
    }

    /**
     * 添加或者修改学生宿舍信息
     *
     * @param building 宿舍信息实体类
     * @return
     */
    @Override
    public Result addAndEditBld(Building building) {
        int row = 0;
        if(building.getId()!=null){
            row = buildingMapper.updateById(building);
        }else{
            row = buildingMapper.insert(building);
        }
        if(row<=0){
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }

    /**
     * 删除宿舍信息
     *
     * @param id 主键
     * @return
     */
    @Override
    public Result deleteById(int id) {
        int row = buildingMapper.deleteById(id);
        if (row<=0){
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }
}

