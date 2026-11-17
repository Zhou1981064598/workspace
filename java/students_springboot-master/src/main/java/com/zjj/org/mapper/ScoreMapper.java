package com.zjj.org.mapper;

import com.zjj.org.base.BaseMapper;
import com.zjj.org.entity.Score;
import com.zjj.org.entity.bean.ScoreBean;
import com.zjj.org.entity.vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zjj
 * @date 2022-05-23
 */
@Mapper
@Repository
public interface ScoreMapper extends BaseMapper<Score> {

    List<ScoreBean> selectScore(ScoreVo scoreVo);

}
