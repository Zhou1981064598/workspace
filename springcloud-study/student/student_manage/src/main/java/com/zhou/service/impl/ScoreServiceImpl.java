package com.zhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.base.Result;
import com.zhou.base.ResultCode;
import com.zhou.entity.Score;
import com.zhou.entity.Student;
import com.zhou.entity.bean.EditScoreBean;
import com.zhou.entity.bean.ScoreBean;
import com.zhou.entity.vo.ScoreVo;
import com.zhou.mapper.ScoreMapper;
import com.zhou.mapper.StudentMapper;
import com.zhou.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private StudentMapper studentMapper;


    /**
     * 分页查询
     *
     * @param scoreVo
     * @return
     */
    @Override
    public Result selectByPage(ScoreVo scoreVo) {
        //        分页
        PageHelper.startPage(scoreVo.getPage(),scoreVo.getLimit());
        //        查询
        List<ScoreBean> scoreList= scoreMapper.selectScore(scoreVo);
        //        获取分页数据
        PageInfo<ScoreBean> pageInfo=new PageInfo<>(scoreList);
        return Result.success(pageInfo.getList(),(int)pageInfo.getTotal());
    }

    /**
     * 添加或修改
     *
     * @param scoreBean
     * @return
     */
    @Override
    public Result editScore(EditScoreBean scoreBean) {
        int row = 0;
        if(scoreBean.getId()!=null){
            row = scoreMapper.updateById(scoreBean);
        }else{
            //1、判断是否有该学生，通过学生姓名和学号
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("stu_name",scoreBean.getStuName());
            queryWrapper.eq("stu_no",scoreBean.getSno());
            Student stu = studentMapper.selectOne(queryWrapper);
            if(stu==null){
                return Result.failure(ResultCode.STU_NOT_EXIST);
            }
            //2、判断该学生是否已经存在该科目的成绩
            QueryWrapper<Score> wrapper = new QueryWrapper<>();
            wrapper.eq("sno",scoreBean.getSno());
            wrapper.eq("subject",scoreBean.getSubject());
            List<Score> scores= scoreMapper.selectList(wrapper);
            if(scores!=null && scores.size()>0){
                return Result.failure(ResultCode.SCORE_HAS_EXIST);
            }
            row = scoreMapper.insert(scoreBean);
        }
        if(row<=0){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        return Result.success();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Result delScore(int id) {
        int row = scoreMapper.deleteById(id);
        if(row<=0){
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }
}
