package com.dmsdbj.integral.training.provider.controller.advice;

import com.dmsdbj.cloud.tool.RequestLogging;
import com.dmsdbj.cloud.tool.RestStatus;
import com.dmsdbj.cloud.tool.StatusCode;
import com.dmsdbj.cloud.tool.exception.IllegalValidateException;
import com.dmsdbj.cloud.tool.exception.RequestAttributeConst;
import com.dmsdbj.cloud.tool.exception.RestStatusException;
import com.dmsdbj.cloud.tool.response.ErrorEntity;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * controller层异常拦截处理
 * @author liuziteng
 */
@Slf4j
@ControllerAdvice
public class FaultBarrier {

    private static final ImmutableMap<Class<? extends Throwable>, RestStatus> EXCEPTION_MAPPINGS;
    
    static {
        final ImmutableMap.Builder<Class<? extends Throwable>, RestStatus> builder = ImmutableMap.builder();
        
        // HTTP Request Method不存在
        // 其他未被发现的异常
        // SpringMVC中参数类型转换异常，常见于String找不到对应的ENUM而抛出的异常

        //传递Date参数报错
        builder.put(MethodArgumentTypeMismatchException.class, StatusCode.INVALID_PARAMS_CONVERSION);
        //检查HTTP请求方法和参数条件
        builder.put(UnsatisfiedServletRequestParameterException.class, StatusCode.INVALID_PARAMS_CONVERSION);
        //用于Hibernate Validator的校验异常
        builder.put(IllegalValidateException.class, StatusCode.INVALID_PARAMS_CONVERSION);
        //表明向方法传递了一个不合法或不正确的参数
        builder.put(IllegalArgumentException.class, StatusCode.INVALID_PARAMS_CONVERSION);
        // HTTP Request Method不存在 405 (Method Not Allowed)
        builder.put(HttpRequestMethodNotSupportedException.class, StatusCode.REQUEST_METHOD_NOT_SUPPORTED);
        // 要求有RequestBody的地方却传入了NULL  400 (Bad Request)
        builder.put(HttpMessageNotReadableException.class, StatusCode.HTTP_MESSAGE_NOT_READABLE);
        // 通常是操作过快导致DuplicateKey
        builder.put(DuplicateKeyException.class, StatusCode.DUPLICATE_KEY);
        // 其他未被发现的异常
        builder.put(Exception.class, StatusCode.SERVER_UNKNOWN_ERROR);
        EXCEPTION_MAPPINGS = builder.build();
    }
    
    /**
     * <strong>Request域取出对应错误信息</strong>, 封装成实体ErrorEntity后转换成JSON输出
     *
     * @param e       {@code StatusCode}异常
     * @param request HttpServletRequest
     * @return ErrorEntity
     * @see ErrorEntity
     * @see StatusCode
     */
    @ResponseBody
    @RequestLogging
    @ExceptionHandler(RestStatusException.class)
    public Object restStatusException(Exception e, HttpServletRequest request) {
        // 取出存储在Shift设定在Request Scope中的ErrorEntity
        return request.getAttribute(e.getMessage());
    }
    
    /**
     * <strong>Request域取出对应错误信息</strong>, 封装成实体ErrorEntity后转换成JSON输出
     *
     * @param e       {@code IllegalValidateException}异常
     * @param request HttpServletRequest
     * @return ErrorEntity
     * @see ErrorEntity
     */
    @ResponseBody
    @RequestLogging
    @ExceptionHandler(IllegalValidateException.class)
    public Object illegalValidateException(Exception e, HttpServletRequest request) {
        log.error("request id: {}\r\nexception: {}", request.getAttribute( RequestAttributeConst.REQUEST_ID), e);
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        // 取出存储在Request域中的Map
        return request.getAttribute(e.getMessage());
    }
    
    @ResponseBody
    @RequestLogging
    @ExceptionHandler(Exception.class)
    public ErrorEntity exception(Exception e, HttpServletRequest request) {
        log.error("request id: {}\r\nexception: {}", request.getAttribute(RequestAttributeConst.REQUEST_ID), e);
        final RestStatus status = EXCEPTION_MAPPINGS.get(e.getClass());
        final ErrorEntity error;
        if (status != null) {
            error = new ErrorEntity(status);
        } else {
            error = new ErrorEntity(StatusCode.SERVER_UNKNOWN_ERROR);
        }
        return error;
        
    }
    
}
