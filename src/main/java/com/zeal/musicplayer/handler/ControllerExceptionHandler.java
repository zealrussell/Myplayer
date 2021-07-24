package com.zeal.musicplayer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @date 2021/4/25 22:04
 *
 * 处理异常
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e){
        logger.error("Request :{},Exception :{}",request.getRequestURI(),e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("requesturl",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("templates/error/error");
        return mv;
    }

}
