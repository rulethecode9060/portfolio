package com.portfolio.sns.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException{

    /**
     * 에러 내용
     */
    private Map<String, String> errorMap;

    /**
     * 기본 생성자
     * @param message
     */
    public CustomException(String message){
        super(message);
    }

    /**
     * 에러 내용 필드가 추가된 생성자
     * @param message
     * @param errorMap
     */
    public CustomException(String message, Map<String, String> errorMap){
        super(message);
        this.errorMap = errorMap;
    }

}
