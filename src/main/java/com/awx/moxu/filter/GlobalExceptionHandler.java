package com.awx.moxu.filter;

import cn.hutool.core.util.StrUtil;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.exception.ApiCode;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @description: 自定义异常处理
 * @author: DT
 * @date: 2021/4/19 21:51
 * @version: v1.0
 */

//    @ExceptionHandler注解标注的方法：用于捕获Controller中抛出的不同类型的异常，从而达到异常全局处理的目的；
//    @InitBinder注解标注的方法：用于请求中注册自定义参数的解析，从而达到自定义请求参数格式的目的；
//    @ModelAttribute注解标注的方法：表示此方法会在执行目标Controller方法之前执行

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @ModelAttribute
    public void doFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        logger.error("请求路径：{}",path);
        if (isSkip(path)) {
            return;
        }
        Enumeration<String> headers = request.getHeaders(AuthProvider.AUTH_KEY);
        String headerToken = headers.nextElement();
        if (StrUtil.isBlank(headerToken)){
            throw new BizException(401, "缺失令牌,鉴权失败");
        }
        String token = JwtUtils.getToken(headerToken);
        Boolean aBoolean = JwtUtils.parseJwt(token);
        if (!aBoolean){
            throw new BizException(401, "请求未授权");
        }
    }
    private boolean isSkip(String path) {
        System.out.println(path);
        return AuthProvider.getDefaultSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
    }

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public R bizExceptionHandler(HttpServletRequest req, @NotNull BizException e){
        logger.error("发生业务异常！原因是：{}",e.getMsg());
        return R.fail(e.getCode(),e.getMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return R.fail(ApiCode.NULL_REQUEST_PARAMETER);
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return R.fail(ApiCode.SERVER_ERROR);
    }
}
