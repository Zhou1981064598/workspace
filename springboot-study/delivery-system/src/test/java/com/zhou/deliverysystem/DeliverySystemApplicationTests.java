package com.zhou.deliverysystem;

import com.zhou.deliverysystem.dao.MissionDao;
import com.zhou.deliverysystem.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DeliverySystemApplicationTests {
    @Resource
    MissionService missionService;
    @Resource
    PackageSendInfService packageSendInfService;
    @Resource
    UserService userService;
    @Resource
    OrderService orderService;
    @Resource
    EvaluateService evaluateService;
    @Resource
    RiderApplyService riderApplyService;
    @Test
    void contextLoads() {
//        System.out.println(userService.getAllRider());
    }
}
