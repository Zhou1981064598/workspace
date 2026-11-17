package com.zhou.springbootrbac.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
@Component
public class SecurityPermissionEvaluatorHandler implements PermissionEvaluator {

    @Autowired
    AntPathMatcher antPathMatcher;

    @Autowired
    HttpServletRequest request;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (antPathMatcher.match(authority.getAuthority(),request.getRequestURI())) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
