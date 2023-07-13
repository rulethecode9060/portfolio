package com.portfolio.sns.handler;

import com.portfolio.sns.exception.CustomValidationException;
import com.portfolio.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Controller exception handler.
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    /**
     * @param e
     * @return string
     * @methodName : validationException
     * @author : rulethecode9060
     * @date : 2023.07.13
     * @description : 일반적인 유효성 검증 실패 처리 핸들러
     */
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        return Script.back(e.getMessage());
    }


}
