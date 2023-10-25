package cn.evanzuo.admin.business.menu.common;

import cn.evanzuo.admin.business.menu.api.CommonResult;
import cn.evanzuo.admin.business.menu.api.IErrorCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonErrorHandler {
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult argumentExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        System.out.println("发生参数异常:{}" + message);
        return CommonResult.failed(new IErrorCode() {
            @Override
            public long getCode() {
                return 400;
            }

            @Override
            public String getMessage() {
                return e.getMessage();
            }
        }, message);
    }
    // @ExceptionHandler用来设置处理哪个异常
    @ExceptionHandler({RuntimeException.class})
    public CommonResult passwordException(Exception e) {
        System.out.println("发生运行时异常:{}" + e.fillInStackTrace());
        return CommonResult.failed(new IErrorCode() {
            @Override
            public long getCode() {
                return 500;
            }

            @Override
            public String getMessage() {
                return e.getMessage();
            }
        }, e.getMessage());
    }

    /**
     * 参数校验异常
     */

}
