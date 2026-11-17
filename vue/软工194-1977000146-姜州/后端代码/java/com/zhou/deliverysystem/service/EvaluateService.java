package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.Evaluate;

import java.util.List;


public interface EvaluateService extends IService<Evaluate> {
    void evaluate(Evaluate evaluate);
    Double getScore(Long courierId);
}
