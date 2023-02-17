package com.awx.moxu.utils.filter;

import cn.hutool.core.util.StrUtil;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.R.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 鉴权认证
 * @author anLiHang
 * @date 2023/2/17
 */

@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements Filter {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        if (isSkip(path)) {
            filterChain.doFilter(request, servletResponse);
            return;
        }
        Enumeration<String> headers = request.getHeaders(AuthProvider.AUTH_KEY);
        System.out.println("鉴权");
        String headerToken = headers.nextElement();
        if (StrUtil.isBlank(headerToken)){
//            return R.fail(401, "缺失令牌,鉴权失败");
        }
        String token = JwtUtils.getToken(headerToken);
        Boolean aBoolean = JwtUtils.parseJwt(token);
        if (aBoolean){
//            throw new BusinessException(401, "请求未授权");
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器一销毁了");
    }

    private boolean isSkip(String path) {
        return AuthProvider.getDefaultSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
    }

}
