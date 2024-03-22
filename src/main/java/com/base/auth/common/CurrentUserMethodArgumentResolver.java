package com.base.auth.common;

import com.base.auth.annotation.CurrentUser;
import com.base.auth.entity.SysAuth;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;


public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(SysAuth.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 获取拦截器中获取的当前用户信息
        SysAuth sysAuth = (SysAuth) nativeWebRequest.getAttribute("sysAuth", RequestAttributes.SCOPE_REQUEST);
        if (sysAuth != null) {
            return sysAuth;
        }
        // 如果当前用户信息为null  则抛出异常
        throw new MissingServletRequestPartException("sysAuth");
    }
}

