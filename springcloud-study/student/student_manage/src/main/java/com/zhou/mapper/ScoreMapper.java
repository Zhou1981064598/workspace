package com.zhou.mapper;

import com.zhou.base.BaseMapper;
import com.zhou.entity.Score;
import com.zhou.entity.bean.ScoreBean;
import com.zhou.entity.vo.ScoreVo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ScoreMapper extends BaseMapper<Score> {

    List<ScoreBean> selectScore(ScoreVo scoreVo);

}
