package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.enenty.RiderApply;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.ResultCode;

import java.util.List;

public interface RiderApplyService extends IService<RiderApply> {
    ResultCode submitApply(RiderApply riderApply);
   Paging<RiderApply> getAllApply(Paging<RiderApply> paging);
   RiderApply getMyApply();
   RiderApply getApplyById(Long userId);
   void examine(Long userId,Integer auditResult);
}
