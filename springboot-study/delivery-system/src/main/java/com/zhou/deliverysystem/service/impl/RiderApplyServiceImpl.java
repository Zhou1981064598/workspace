package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.RiderApplyDao;
import com.zhou.deliverysystem.dao.RoleDao;
import com.zhou.deliverysystem.enenty.RiderApply;
import com.zhou.deliverysystem.enenty.Role;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.ResultCode;
import com.zhou.deliverysystem.service.RiderApplyService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class RiderApplyServiceImpl extends ServiceImpl<RiderApplyDao, RiderApply> implements RiderApplyService {
    @Resource
    private RiderApplyDao riderApplyDao;

    @Resource
    RoleDao roleDao;

    @Resource
    UserService userService;
    @Override
    public ResultCode submitApply(RiderApply riderApply) {
        RiderApply apply=riderApplyDao.selectOne(new QueryWrapper<RiderApply>().select("user_id").eq("user_id",riderApply.getUserId()).eq("status",1));
        List<Role> list=roleDao.selectListByUserId(riderApply.getUserId());
        List<String> list1=new LinkedList<>();
        for (Role role:list){
            list1.add(role.getRoleName());
        }
       if (list1.contains("骑手")){
           return ResultCode.FAILURE_APPLY_RIDER_EXIST;
       }
        if (!ObjectUtils.isEmpty(apply)) {
            return ResultCode.FAiLURE_APPLY_EXIST;
        }
        int result=riderApplyDao.insert(riderApply);
        if (result==1){
            return ResultCode.SUCCESS;
        }else {
            return ResultCode.APPLY_FAILURE;
        }
    }

    @Override
    public Paging<RiderApply> getAllApply(Paging<RiderApply> paging) {
        Page<RiderApply> page=riderApplyDao.selectPage(new Page<>(paging.getCurrentPage(),paging.getPageSize()),
                new QueryWrapper<RiderApply>().select().eq("status",1));
        return Paging.build(page.getTotal(),paging.getCurrentPage(),paging.getPageSize(),page.getRecords());
    }

    @Override
    public RiderApply getMyApply() {
        return riderApplyDao.selectMyLastApply(userService.getuser().getId());
    }

    @Override
    public RiderApply getApplyById(Long userId) {
        try {
            return  riderApplyDao.selectOne(new QueryWrapper<RiderApply>().eq("user_id",userId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void examine(Long userId,Integer auditResult) {
        RiderApply riderApply=new RiderApply();
        riderApply.setStatus(2);
        riderApply.setAuditResult(auditResult);
        UpdateWrapper<RiderApply> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("user_id",userId);
        riderApplyDao.update(riderApply,updateWrapper);
    }


}