package com.zhou.service;

import com.zhou.base.Result;
import com.zhou.entity.bean.EditScoreBean;
import com.zhou.entity.vo.ScoreVo;

import org.springframework.stereotype.Service;


@Service
public interface ScoreService {
    /**
     * 分页查询
     * @param scoreVo
     * @return
     */
    Result selectByPage(ScoreVo scoreVo);

    /**
     * 添加或修改
     * @param scoreBean
     * @return
     */
    Result editScore(EditScoreBean scoreBean);

    /**
     * 删除
     * @param id
     * @return
     */
    Result delScore(int id);
}
