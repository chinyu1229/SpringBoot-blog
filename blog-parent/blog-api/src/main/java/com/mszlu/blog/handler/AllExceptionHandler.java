package com.mszlu.blog.handler;

import com.mszlu.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//對於加了@Controller注解的method進行攔截處理 -> AOP的實現
@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json資訊
    public Result doException(Exception ex){
        ex.printStackTrace(); //打印在後端的訊息
        return Result.fail(-999,"系統異常，請稍後再試");
    }

}
