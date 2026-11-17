package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.EvaluateDao;
import com.zhou.deliverysystem.enenty.Evaluate;
import com.zhou.deliverysystem.service.EvaluateService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateDao, Evaluate> implements EvaluateService {
    @Resource
    EvaluateDao evaluateDao;

    @Resource
    UserService userService;

    @Override
    public void evaluate(Evaluate evaluate) {
        if (evaluate.getCourierId()!=null){
            Long userId=userService.getuser().getId();
            evaluate.setUserId(userId);
            evaluateDao.insert(evaluate);
            userService.setScore(evaluate.getCourierId());
        }
    }

    @Override
    public Double getScore(Long courierId) {
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper<Evaluate>();
        queryWrapper.select("avg(score) as evaluationScore");
        queryWrapper.eq("courier_id",courierId);
        Evaluate evaluate=evaluateDao.selectOne(queryWrapper);
        Double evaluationScore= (double) 0;
        //注意，空指针问题
        if (evaluate== null){
            return evaluationScore ;
        }else{
            return  evaluate.getEvaluationScore();
        }
    }
}
