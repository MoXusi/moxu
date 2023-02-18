//package com.awx.moxu.filter;
//
//import cn.hutool.core.util.StrUtil;
//import com.awx.moxu.utils.JwtUtils;
//import com.awx.moxu.utils.R.R;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.jetbrains.annotations.NotNull;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Enumeration;
//
///**
// * 鉴权认证
// * @author anLiHang
// * @date 2023/2/17
// */
//
//@Slf4j
//@Component
//@AllArgsConstructor
//public class AuthFilter implements Filter {
//
//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String path = request.getRequestURI();
//        if (isSkip(path)) {
//            filterChain.doFilter(request, servletResponse);
//            System.out.println(path);
//            return;
//        }
//        Enumeration<String> headers = request.getHeaders(AuthProvider.AUTH_KEY);
//        System.out.println("鉴权:"+path);
//        String headerToken = headers.nextElement();
//        if (StrUtil.isBlank(headerToken)){
//            throw new BizException(401, "缺失令牌,鉴权失败");
//        }
//        String token = JwtUtils.getToken(headerToken);
//        Boolean aBoolean = JwtUtils.parseJwt(token);
//        if (aBoolean){
//            throw new BizException(401, "请求未授权");
//        }
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("过滤器一销毁了");
//    }
//
//    private boolean isSkip(String path) {
//        return AuthProvider.getDefaultSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
//    }
//
//}
