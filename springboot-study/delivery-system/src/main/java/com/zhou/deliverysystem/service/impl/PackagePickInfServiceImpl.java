package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.UserDao;
import com.zhou.deliverysystem.enenty.Order;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.dao.PackagePickInfDao;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.service.OrderService;
import com.zhou.deliverysystem.service.PackagePickInfService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 19810
* @description 针对表【package_pick_inf】的数据库操作Service实现
* @createDate 2023-04-21 18:37:41
*/
@Service
public class PackagePickInfServiceImpl extends ServiceImpl<PackagePickInfDao, PackagePickInf> implements PackagePickInfService {
    @Resource
    PackagePickInfDao packagePickInfDao;

    @Resource
    UserService userService;

    @Resource
    OrderService orderService;

    @Override
    public String pick(PackagePickInf packagePickInf) {
        if (packagePickInf!=null){
            packagePickInf.setType("快递代取");
            packagePickInf.setStatus(1);
            packagePickInf.setIdCode(packagePickInf.getIdCode());
            packagePickInf.setUserId(userService.getuser().getId());
            Order order=new Order();
            order.setType("快递代取");
            order.setStatus(1);
            orderService.addOrder(order);
            packagePickInf.setOrderId(order.getId());
        int result=packagePickInfDao.insert(packagePickInf);
            if (result==1){
                orderService.setOrderInfId(order.getId(),packagePickInf.getId());
            return "发布成功";
        }else {
            return "发布失败";
        }
        }
        return "发布失败";
    }

    @Override
    public Paging<PackagePickInf> getPackageInf(Paging<PackagePickInf> paging) {

       Page<PackagePickInf> page=packagePickInfDao.selectPage(
               new Page<>(paging.getCurrentPage(),paging.getPageSize()),
               new QueryWrapper<PackagePickInf>().select().eq("status",1));
       return Paging.build(page.getTotal(),paging.getCurrentPage(),paging.getPageSize(),page.getRecords());
    }

    @Override
    public PackagePickInf selectPackageInfById(Long id) {
        if ( packagePickInfDao.selectById(id)!=null){
            return packagePickInfDao.selectById(id);
        }else {
            return null;
        }
    }

    @Override
    public void updatePackageStatusById(Long id) {
        UpdateWrapper<PackagePickInf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        PackagePickInf packagePickInf=new PackagePickInf();
        packagePickInf.setStatus(2);
        packagePickInfDao.update(packagePickInf,updateWrapper);

    }

    @Override
    public void cancelMission(Long id) {
        UpdateWrapper<PackagePickInf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        PackagePickInf packagePickInf=new PackagePickInf();
        packagePickInf.setStatus(1);
        packagePickInfDao.update(packagePickInf,updateWrapper);

    }
}
