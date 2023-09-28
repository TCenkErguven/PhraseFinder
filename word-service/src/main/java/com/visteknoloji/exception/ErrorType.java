package com.visteknoloji.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(5100,"Eternal Error",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parameter Error",HttpStatus.BAD_REQUEST),
    PHRASE_ALREADY_EXIST(3200,"Phrase Already Exist",HttpStatus.BAD_REQUEST),




    ;


    private int code;
    private String message;
     HttpStatus httpStatus;
}
