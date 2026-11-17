package com.example.SpringCloudStudy.microservice.rbac.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Component
public class RbacPermissionEvaluatorHandler implements PermissionEvaluator {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    AntPathMatcher antPathMatcher;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> antPathMatcher
                        .match(auth.getAuthority(), httpServletRequest.getRequestURI()));
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
