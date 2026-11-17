package com.zhou.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhou.base.BaseController;
import com.zhou.base.Result;
import com.zhou.entity.bean.EditScoreBean;
import com.zhou.entity.vo.ScoreVo;
import com.zhou.service.ScoreService;
import com.zhou.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@CrossOrigin(origins = "*") //允许所有域名访问

@RequestMapping(value = "/score")
public class ScoreController extends BaseController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private SubjectService subjectService;
    /**
     * 跳转页面
     * @return
     */
    @RequestMapping(value = "toList")
    private ModelAndView toList(){
        ModelAndView mav = new ModelAndView();

        mav.addObject("subList",subjectService.selectAll());

        mav.setViewName("score/list");
        return mav;
    }

    /**
     * 分页查询
     * @param scoreVo
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    private JSONObject list(ScoreVo scoreVo){
        Result result =scoreService.selectByPage(scoreVo);
        return createResponseJson(result);
    }

    /**
     * 添加或者修改成绩
     * @param scoreBean
     * @return
     */
    @RequestMapping(value = "/handling")
    @ResponseBody
    private JSONObject edit(@RequestBody EditScoreBean scoreBean){
        Result result =scoreService.editScore(scoreBean);
        return createResponseJson(result);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public JSONObject delete(int id){
        Result result = scoreService.delScore(id);
        return createResponseJson(result);
    }

}
