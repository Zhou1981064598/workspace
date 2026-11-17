package com.zhou.springbootrbac.uitls;

import com.zhou.springbootrbac.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestUtil {
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取请求头中的 token
     * @return  /
     */
    public String getHeaderToken() {
        return request.getHeader(SecurityConstant.REQUEST_HEADER_AUTHORIZATION_KEY);
    }

    /**
     * 获取请求头中的 username
     * @return  /
     */
    public String getHeaderUsername() {
        String requestToken = getHeaderToken();
        String username = null;
        if (!StringUtils.isEmpty(requestToken)) {
            // 解析 token 并且提取 用户名
            try {
                username = JwtUtil.parseJWT(requestToken).getSubject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return username;
    }
}
