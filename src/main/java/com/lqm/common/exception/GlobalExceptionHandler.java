package com.lqm.common.exception;

import com.lqm.common.api.CommonResult;
import com.lqm.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理 , 捕获ApiException异常
 * Created by macro on 2020/2/27.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  业务抛出信息
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object Exception(Exception exp) {
        return CommonResult.failed(ResultCode.BAD_REQUEST.getCode(),exp.getMessage());
    }
}
