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
    AUTHOR_NOT_FOUND(4200,"Author Not Found",HttpStatus.NOT_FOUND),
    DOES_NOT_MATCH(4300,"Phrase Does Not Match",HttpStatus.BAD_REQUEST),
    NOT_FOUND(4400,"Word Holder Not Found",HttpStatus.NOT_FOUND),



    ;


    private int code;
    private String message;
     HttpStatus httpStatus;
}
