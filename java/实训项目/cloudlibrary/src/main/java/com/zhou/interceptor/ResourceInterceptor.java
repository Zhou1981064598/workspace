package com.zhou.interceptor;

import com.zhou.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResourceInterceptor extends HandlerInterceptorAdapter {
    private List<String> ignoreUrl;
    public ResourceInterceptor(List<String> ignoreUrl) {
        this.ignoreUrl=ignoreUrl;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws  Exception{
        User user=(User) request.getSession().getAttribute("USER_SESSION");
        //获取请求的路径
        String uri=request.getRequestURI();
        //如果用户是已登录状态，判断访问的资源是否有权限
        if(user!=null) {
            //如果是管理员。放行
            if ("admin".equals(user.getRole())){
                return true;
            }
            //如果是普通用户
            else {
                for (String url:ignoreUrl){
                    //访问的资源不是管理员权限的资源，放行
                    if (uri.indexOf(url)>=0){
                        return true;
                    }
                }
            }
        }
        //用户登录的相关请求，也放行
        if(uri.indexOf("login")>=0)
        {
            return true;
        }
        //其他情况都直接跳转到登录的页面
        request.setAttribute("msg","您还没有登录，请先登录");
        request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
        return false;
    }

}