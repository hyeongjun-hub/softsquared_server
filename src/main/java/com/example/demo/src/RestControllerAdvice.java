package com.example.demo.src;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.ValidationErrorResponse;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    // BaseException
    @ExceptionHandler({BaseException.class})
    public BaseResponse<Object> handleException(BaseException ex) {
        logger.warn("error", ex.getMessage());
        return new BaseResponse<>(ex.getStatus());
    }

    // Spring validation Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ValidationErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();

        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrorResponse.setIsSuccess(false);
            validationErrorResponse.setStatus(400);
            validationErrorResponse.setMessage(fieldError.getDefaultMessage());
        }

        return validationErrorResponse;
    }

    // 400
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> BadRequestException(final RuntimeException ex) {
        logger.warn("error", ex.getMessage());
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
