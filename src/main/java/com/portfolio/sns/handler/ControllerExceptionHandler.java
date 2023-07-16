package com.portfolio.sns.handler;

import com.portfolio.sns.dto.common.CommonResponseDto;
import com.portfolio.sns.exception.CustomAPIException;
import com.portfolio.sns.exception.CustomException;
import com.portfolio.sns.exception.CustomValidationException;
import com.portfolio.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * @param e
     * @return string
     * @methodName : exception
     * @author : rulethecode9060
     * @date : 2023.07.15
     * @description : 일반적인 커스텀 예외 처리 핸들러
     */
    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e){
        return Script.back(e.getMessage());
    }

    /**
     * @param e
     * @return responseEntity entity
     * @methodName : apiException
     * @author : rulethecode9060
     * @date : 2023.07.16
     * @description : API 유효성 검증 실패 처리 핸들러
     */
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> apiValidationException(CustomValidationException e){
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param e
     * @return response entity
     * @methodName : apiException
     * @author : rulethecode9060
     * @date : 2023.07.16
     * @description : 일반적인 API 커스텀 예외 처리 핸들러
     */
    @ExceptionHandler(CustomAPIException.class)
    public ResponseEntity<?> apiException(CustomAPIException e){
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }


}
