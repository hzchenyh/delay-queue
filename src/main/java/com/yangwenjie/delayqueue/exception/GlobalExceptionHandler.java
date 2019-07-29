package com.yangwenjie.delayqueue.exception;

import com.yangwenjie.delayqueue.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Create By IntelliJ IDEA
 * 全局异常处理
 *
 * @author Yang WenJie
 * @date 2018/1/5 11:57
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统出错", e);
        return Result.error().put("exception", e.getMessage());
    }
}
