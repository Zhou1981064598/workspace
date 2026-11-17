package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.pojo.Paging;

import java.util.List;

/**
* @author 19810
* @description 针对表【package_pick_inf】的数据库操作Service
* @createDate 2023-04-21 18:37:41
*/
public interface PackagePickInfService extends IService<PackagePickInf> {
        String pick(PackagePickInf packagePickInf);
        Paging<PackagePickInf> getPackageInf(Paging<PackagePickInf> paging);
        PackagePickInf selectPackageInfById(Long id);
        void updatePackageStatusById(Long id);
        void cancelMission(Long id);
}
