package com.example.demo.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JwtUtil {
    // 签名不应该泄露给他人非常危险 这一点要非常注意
    private static final String secret = "AsFWid-jrT9kI-DFWKh9-Nijdw1-0KJOL1";

    public static String InsertToken(Integer userAccount, Integer id) {

        Calendar instanceCalendar = Calendar.getInstance(); // 提供日期格式的处理

        instanceCalendar.add(Calendar.DATE, 3); // 单位为天 3天后过期

        JWTCreator.Builder builder = JWT.create(); // 创建JWT builder

        builder.withClaim("userId", userAccount).withClaim("id", id);


        return builder.withExpiresAt(instanceCalendar.getTime())//  指定指令令牌过期时间 上面指定时间单位为天

                .sign(Algorithm.HMAC256(secret));  //单位可以自行更改,最后将生成的token返回
    }


    public static DecodedJWT SelectToken(String token) { // 验证合法性此时报错就是没有通过验证
        // 为你列出常见的报错
        /*
         * - SignatureVerificationException : 签名不一致异常
         * - TokenExpiredException: 令牌过期异常
         * - AlgorithmMismatchException: 算法不匹配异常
         * - InvalidclaimException: 失效的payload异常
         *
         */
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build(); // 传入签名
        DecodedJWT decodedJWT;
        try {
            System.out.println(token);
            decodedJWT = jwtVerifier.verify(token);
        } catch (NullPointerException e) {
            throw new JWTVerificationException("非法访问");
        }
        return decodedJWT;// 最后将生成的verify对象返回

    }
}
