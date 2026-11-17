package com.example.demo.interceptor;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //获取token
        String token = request.getHeader("token");
        //解析toke，获取用户信息
        /*可能的报错信息
         * - SignatureVerificationException : 签名不一致异常
         * - TokenExpiredException: 令牌过期异常
         * - AlgorithmMismatchException: 算法不匹配异常
         * - InvalidClaimException: 失效的payload异常
         */
        try {
            DecodedJWT verify = JwtUtil.SelectToken(token);
        } catch (InvalidClaimException | NullPointerException e) {
            System.out.println("拦截来自 " + uri + " 的无效token");
            response.sendRedirect("/Welcome/toLogin");
            return false;
        }
        return true;
    }

}
