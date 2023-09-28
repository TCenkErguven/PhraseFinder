package com.visteknoloji.exception;

import lombok.Getter;

@Getter
public class WordServiceException extends RuntimeException{
    private final ErrorType errorType;

    public WordServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public WordServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }



}
