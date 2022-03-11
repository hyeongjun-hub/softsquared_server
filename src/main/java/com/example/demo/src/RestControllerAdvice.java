package com.example.demo.src;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({BaseException.class})
    public BaseResponse<Object> handleException(BaseException exc) {
        logger.warn("error", exc);
        return new BaseResponse<>(exc.getStatus());
    }

    // 400
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> BadRequestException(final RuntimeException ex) {
        logger.warn("error", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
