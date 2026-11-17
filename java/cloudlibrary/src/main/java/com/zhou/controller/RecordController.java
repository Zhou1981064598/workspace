package com.zhou.controller;

import com.github.pagehelper.Page;
import com.zhou.domain.Record;
import com.zhou.domain.User;
import com.zhou.enetity.PageResult;
import com.zhou.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordService recordService;
    @RequestMapping("/searchRecords")
    /**
     * 查询当前借阅记录
     * @param record   record借阅记录的查询条件
     * @parm pageNum    pageNum当前页码
     * @parm pageSize   pageSize每页显示数量
     */
    public ModelAndView searchRecord(Record record, Integer pageNum, Integer pageSize, HttpServletRequest request){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        //获取当前登录用户
        User user=(User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult=recordService.searchRecords(record,user,pageNum, pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("record.");
        //将查询结果的数据存放到ModelAndView的对象中
        modelAndView.addObject("pageResult",pageResult);
        //将查询到的数据返回到页面，用于回显到查询的输入框中
        modelAndView.addObject("search",record);
        //将当前页码返回到页面，用于分页插件的分页显示
        modelAndView.addObject("pageNum",pageNum);
        //将当前查询的控制器类路径返回到页面，页码变化时可继续向该路径发送请求
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }
}
