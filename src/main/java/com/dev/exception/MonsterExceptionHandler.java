package com.dev.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.entity.system.ResultTo;

/**
 * @ClassName: HeroExceptionHandler
 * @Description: HeroExceptionHandler
 * @author: wen.dai
 * @date: 2018年5月22日 下午6:44:39
 */
@ControllerAdvice
public class MonsterExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultTo<Object> heroRuntimeException(MissingServletRequestParameterException e) {
    	ResultTo<Object> resultTo=new ResultTo<>();
        logger.error("请求参数异常: " + e.getMessage());
        resultTo.commonException("请求参数异常: " + e.getMessage());
        return resultTo;
    }

    @ExceptionHandler(MonsterException.class)
    @ResponseBody
    public ResultTo<Object> heroExceptionHandler(MonsterException e) {
    	ResultTo<Object> resultTo=new ResultTo<>();
        logger.error("系统异常" , e);
        resultTo.commonException("系统异常: " + e.getMessage());
        return resultTo;
    }

    @ExceptionHandler(MonsterRuntimeException.class)
    @ResponseBody
    public ResultTo<Object> heroRuntimeException(MonsterRuntimeException e) {
    	ResultTo<Object> resultTo=new ResultTo<>();
        logger.error("系统运行异常" , e);
        resultTo.commonException("系统运行异常: " + e.getMessage());
        return resultTo;
    }
}
