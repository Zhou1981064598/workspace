package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.PackagePickInfDao;
import com.zhou.deliverysystem.enenty.Order;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.enenty.PackageSendInf;
import com.zhou.deliverysystem.dao.PackageSendInfDao;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.service.OrderService;
import com.zhou.deliverysystem.service.PackageSendInfService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 19810
* @description 针对表【package_send_inf】的数据库操作Service实现
* @createDate 2023-04-21 18:37:41
*/
@Service
public class PackageSendInfServiceImpl extends ServiceImpl<PackageSendInfDao, PackageSendInf>
implements PackageSendInfService {
    @Resource
    PackageSendInfDao packageSendInfDao;

    @Resource
    UserService userService;

    @Resource
    OrderService orderService;


    @Override
    public PackageSendInf selectPackageInfById(Long id) {
        if ( packageSendInfDao.selectById(id)!=null){
            return packageSendInfDao.selectById(id);
        }else {
            return null;
        }
    }

    @Override
    public String send(PackageSendInf packageSendInf) {
        if (packageSendInf!=null){
            packageSendInf.setType("快递代寄");
            packageSendInf.setStatus(1);
            packageSendInf.setUserId(userService.getuser().getId());
            Order order=new Order();
            order.setType("快递代寄");
            order.setStatus(1);
            orderService.addOrder(order);
            packageSendInf.setOrderId(order.getId());
            int result=packageSendInfDao.insert(packageSendInf);
            if (result==1){
                orderService.setOrderInfId(order.getId(),packageSendInf.getId());
                return "发布成功";
            }else {
                return "发布失败";
            }
        }
        return "发布失败";
}

    @Override
    public Paging<PackageSendInf> getPackageInf(Paging<PackageSendInf> paging) {
        Page<PackageSendInf> page=packageSendInfDao.selectPage(
                new Page<>(paging.getCurrentPage(),paging.getPageSize()),
                new QueryWrapper<PackageSendInf>().select().eq("status",1));
        return Paging.build(page.getTotal(),paging.getCurrentPage(),paging.getPageSize(),page.getRecords());
    }

    @Override
    public void updatePackageStatusById(Long id) {
        UpdateWrapper<PackageSendInf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        PackageSendInf packageSendInf=new PackageSendInf();
        packageSendInf.setStatus(2);
        packageSendInfDao.update(packageSendInf,updateWrapper);

    }

    @Override
    public void cancelMission(Long id) {
        UpdateWrapper<PackageSendInf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        PackageSendInf packageSendInf=new PackageSendInf();
        packageSendInf.setStatus(1);
        packageSendInfDao.update(packageSendInf,updateWrapper);
    }
}
