package com.zhou.springbootrbac.constant;

/**
 * Security 中常量
 */
public class SecurityConstant {
    /**
     * 请求头 token 参数名
     */
    public static final String REQUEST_HEADER_AUTHORIZATION_KEY = "authorization";
    /**
     * redis 中存储 username 的 key 名
     */
    public static final String REDIS_USERNAME_KEY = "__USERNAME__";
    /**
     * redis 中存储 token 的 key 名
     */
    public static final String REDIS_TOKEN_KEY = "__TOKEN__";
    /**
     * redis 中存储认证对象 Authorization 的 key 名
     */
    public static final String REDIS_AUTHORIZATION_KEY = "__AUTHORIZATION__";
}
