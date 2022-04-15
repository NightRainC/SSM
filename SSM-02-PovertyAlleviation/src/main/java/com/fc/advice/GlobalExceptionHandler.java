package com.fc.advice;

import com.fc.vo.RestVo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
public class GlobalExceptionHandler {
    //对指定的异常进行捕获
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public RestVo handleDuplicateKeyException(DuplicateKeyException e) {
        e.printStackTrace();
        return new RestVo(-100, false, "用户名重复了", null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public RestVo handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        e.printStackTrace();
        return new RestVo(-200, false, "请求json中的参数异常", null);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public RestVo handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        e.printStackTrace();
        return new RestVo(-300, false, "必须输入json格式的参数", null);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public RestVo handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        return new RestVo(-400, false, "请求中缺少了必要的参数", null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RestVo handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new RestVo(-500, false, "你的操作有误", e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestVo handleException(Exception e) {
        e.printStackTrace();
        return new RestVo(-500, false, "你的操作有误", e);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public  RestVo handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        e.printStackTrace();
        return  new RestVo(-500, false, "请求方式不对", e);
    }


}
