package com.trunip.friendSuggester.aspect;


import com.trunip.friendSuggester.exception.BadRequestException;
import com.trunip.friendSuggester.exception.BaseException;
import com.trunip.friendSuggester.exception.NotFoundException;
import com.trunip.friendSuggester.response.BaseResponse;
import com.trunip.friendSuggester.service.ExceptionHelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @Autowired
    private ExceptionHelperService exceptionHelperService;

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse> BadRequestExceptionHandler(final BadRequestException exp) {
        log.error("exception occurred message is {}, exp: {}", exp.getMessage(), exp);
        return new ResponseEntity<>(
                exceptionHelperService.getBaseResponseFromBaseException(exp),
                HttpStatus.BAD_REQUEST
        );
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> NotFoundExceptionHandler(final NotFoundException exp) {
        log.error("exception occurred message is {}, exp: {}", exp.getMessage(), exp);
        return new ResponseEntity<>(
                exceptionHelperService.getBaseResponseFromBaseException(exp),
                HttpStatus.NOT_FOUND
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> BaseExceptionHandler(final BaseException exp) {
        log.error("exception occurred message is {}, exp: {}", exp.getMessage(), exp);
        return new ResponseEntity<>(
                exceptionHelperService.getBaseResponseFromBaseException(exp),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> ExceptionHandler(final Exception exp) {
        log.error("exception occurred message is {}, exp: {}", exp.getMessage(), exp);
        return new ResponseEntity<>(
                exceptionHelperService.getBaseResponseFromException(exp),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
