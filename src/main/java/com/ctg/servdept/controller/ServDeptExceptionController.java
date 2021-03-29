package com.ctg.servdept.controller;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import com.ctg.servdept.pojo.until.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 自定义异常接口
 */
@CrossOrigin
@ControllerAdvice
public class ServDeptExceptionController {
    @ExceptionHandler(value = ServDeptNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(produces="json/html; charset=UTF-8")
    @ResponseBody

    public Result<String> exception (ServDeptNotFoundException exception){

        int code= exception.getResultCode();
        String Msg = exception.getMsg();
        System.out.println(code+Msg);
        return new Result<String>(code,Msg,"");
    }
}
