package com.zhou.deliverysystem.controller;

import com.zhou.deliverysystem.enenty.Order;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.enenty.PackageSendInf;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.service.MissionService;
import com.zhou.deliverysystem.service.OrderService;
import com.zhou.deliverysystem.service.PackagePickInfService;
import com.zhou.deliverysystem.service.PackageSendInfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    @Resource
    PackagePickInfService packagePickInfService;

    @Resource
    MissionService missionService;

    @Resource
    PackageSendInfService packageSendInfService;

    @GetMapping("/getMySubmitOrder")
    public Result<Map<Object,Object>> getMySubmitOrder() {
        List<Order> orders = orderService.getMyOrderByStatus(new int[]{1});
        Map<Object,Object> map=new HashMap<>();
        if (orders == null) {
            return Result.success("查询成功", null);
        }
        for (Order order : orders) {
            System.out.println(order);
            if ("快递代取".equals(order.getType())) {
                if (packagePickInfService.selectPackageInfById(order.getInfId())!=null){
                    map.put(order.getId(),packagePickInfService.selectPackageInfById(order.getInfId()));}
            }
            if ("快递代寄".equals(order.getType())) {
                {
                    if (packageSendInfService.selectPackageInfById(order.getInfId())!=null){
                        map.put(order.getId(),packageSendInfService.selectPackageInfById(order.getInfId()));
                    }
                }
            }
        }
        return Result.success("查询成功", map);
    }

    @GetMapping("/getMyDoingOrder")
    public Result<List<Object>> getMyDoingOrder() {
        List<Order> orders = orderService.getMyOrderByStatus(new int []{2});
        if (orders == null) {
            return Result.success("查询成功", null);
        }
        List<Object> inf = new LinkedList<>();
        for (Order order : orders) {
            if ("快递代取".equals(order.getType())) {
                List<PackagePickInf> packagePickInfs = new LinkedList<>();
                    PackagePickInf packagePickInf=packagePickInfService.selectPackageInfById(order.getInfId());
                    packagePickInf.setOrderNo(order.getOrderNo());
                    packagePickInf.setCourierId(order.getCourierId());
                    packagePickInfs.add(packagePickInf);
                    inf.addAll(packagePickInfs);
                }

            if ("快递代寄".equals(order.getType())) {
                {
                    List<PackageSendInf> packageSendInfs = new LinkedList<>();
                    packageSendInfs.add(packageSendInfService.selectPackageInfById(order.getInfId()));
                    for (PackageSendInf packageSendInf:packageSendInfs){
                        packageSendInf.setCourierId(order.getCourierId());
                    }
                    inf.addAll(packageSendInfs);
                }
            }

    }        return Result.success("查询成功", inf);

    }

    @GetMapping("/getMyFinishOrder")
    public Result< List<Object>> getMyFinishOrder() {
        List<Order> orders = orderService.getMyOrderByStatus(new int[]{3});
        List<Object> inf = new LinkedList<>();

        if (orders == null) {
            return Result.success("查询成功", null);
        }else {
        for (Order order : orders) {
            if ("快递代取".equals(order.getType())) {
                List<PackagePickInf> packagePickInfs = new LinkedList<>();
                PackagePickInf packageInf=packagePickInfService.selectPackageInfById(order.getInfId());
                packageInf.setMissionId(missionService.getMissionByOrderId(order.getId()).getId());
                packageInf.setCourierId(order.getCourierId());
                packagePickInfs.add(packageInf);
                inf.addAll(packagePickInfs);

            }
            if ("快递代寄".equals(order.getType())) {
                {   List<PackageSendInf> packageSendInfs = new LinkedList<>();
                    PackageSendInf packageInf=packageSendInfService.selectPackageInfById(order.getInfId());
                    packageInf.setMissionId(missionService.getMissionByOrderId(order.getId()).getId());
                    packageInf.setCourierId(order.getCourierId());
                    packageSendInfs.add(packageInf);
                    inf.addAll(packageSendInfs);
                    }
                }
            }
        }
        return Result.success("查询成功", inf);
    }

    @GetMapping("/getMyNoEvaluatedOrder")
    public Result<List<Object>> getMyNoEvaluatedOrder() {
        List<Order> orders = orderService.getMyOrderByStatus(new int[]{4});
        List<Object> inf = new LinkedList<>();

        if (orders == null) {
            return Result.success("查询成功", null);
        }else {
            for (Order order : orders) {
                if ("快递代取".equals(order.getType())) {
                    List<PackagePickInf> packagePickInfs = new LinkedList<>();
                    PackagePickInf packageInf=packagePickInfService.selectPackageInfById(order.getInfId());
                    packageInf.setMissionId(missionService.getMissionByOrderId(order.getId()).getId());
                    packageInf.setOrderNo(order.getOrderNo());
                    packageInf.setCourierId(order.getCourierId());
                    packagePickInfs.add(packageInf);
                    inf.addAll(packagePickInfs);

                }
                if ("快递代寄".equals(order.getType())) {
                    {
                        List<PackageSendInf> packageSendInfs = new LinkedList<>();
                        PackageSendInf packageSendInf=packageSendInfService.selectPackageInfById(order.getInfId());
                        packageSendInf.setOrderNo(order.getOrderNo());
                        packageSendInf.setCourierId(order.getCourierId());
                        packageSendInf.setMissionId(missionService.getMissionByOrderId(order.getId()).getId());
                        packageSendInfs.add(packageSendInf);
                        inf.addAll(packageSendInfs);
                    }
                }
            }}
        return Result.success("查询成功", inf);
    }
    @GetMapping("/deleteOrderById")
    public Result<List<Object>> deleteOrderById(Long id,Long infId,String type) {
        if (id != null&&infId!=null&&type!=null) {
            orderService.deleteOrder(id,infId,type);
            return Result.success("取消成功", null);
        } else {
            return Result.failure(2010, null);
        }
    }
    @GetMapping("/confirmReceipt")
    public Result<List<Object>> confirmReceipt(Long missionId,Long orderId) {
        if (missionId != null&&orderId !=null) {
            orderService.confirmReceipt(orderId,missionId);
            return Result.success("已确认收货", null);
        } else {
            return Result.failure(2010, null);
        }
    }
}