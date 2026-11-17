package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.enenty.PackageSendInf;
import com.zhou.deliverysystem.pojo.Paging;

/**
* @author 19810
* @description 针对表【package_send_inf】的数据库操作Service
* @createDate 2023-04-21 18:37:41
*/
public interface PackageSendInfService extends IService<PackageSendInf> {
    PackageSendInf selectPackageInfById(Long id);
    String send(PackageSendInf packageSendInf);
    Paging<PackageSendInf> getPackageInf(Paging<PackageSendInf> paging);
    void updatePackageStatusById(Long id);
    void cancelMission(Long id);
}
