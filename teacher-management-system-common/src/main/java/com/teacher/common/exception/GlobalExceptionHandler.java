package com.teacher.common.exception;

import com.teacher.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("Unknown error", e);
        return Result.error("服务器内部错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidation(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(msg);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<String> handleDuplicate(DuplicateKeyException e) {
        return Result.error("数据库主键或唯一键冲突");
    }

    @ExceptionHandler(ServiceException.class)
    public Result<String> handleService(ServiceException e) {
        return Result.error(e.getMessage());
    }
}
