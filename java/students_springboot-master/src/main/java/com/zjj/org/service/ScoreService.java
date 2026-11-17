package com.zjj.org.service;

import com.zjj.org.base.Result;
import com.zjj.org.entity.bean.EditScoreBean;
import com.zjj.org.entity.vo.ScoreVo;
import org.springframework.stereotype.Service;

/**
 * @author zjj
 * @date 2022-05-24
 */
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
