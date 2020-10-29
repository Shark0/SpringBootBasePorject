package com.shark.application.configuration.exception;

import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExceptionConfiguration {

    private final MessageSource messageSource;

    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void handlePermissionException() {}

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void handleUnauthorizedException() {}

    @ExceptionHandler(WarningException.class)
    public ResponseDto<?> handleWarningException(WarningException warningException) {
        return responseOf(-1, messageSource.getMessage(warningException.getI18nKey(), null, getLocale()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDto<?> handleConstraintViolationException(ConstraintViolationException exception) {
        String errorMessage = messageSource.getMessage(exception.getMessage(), null, getLocale());
        return responseOf(-1, errorMessage);
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            BindException.class,
            MethodArgumentNotValidException.class})
    public ResponseDto<?> handleParsingRequestParameterException(Exception error) {
        return responseOf(-997, error.getMessage());
    }

    @ExceptionHandler(DataProcessException.class)
    public ResponseDto<?> handleDataProcessException(DataProcessException error) {
        return responseOf(-998, error.getMessage());
    }

    @ExceptionHandler(ResultGenerationException.class)
    public ResponseDto<?> handleResultGenerationException(ResultGenerationException error) {
        return responseOf(-999, error.getMessage());
    }

    private ResponseDto<?> responseOf(int returnCode, String message) {
        ResponseDto<?> responseDto = new ResponseDto<>();
        responseDto.setReturnCode(returnCode);
        responseDto.setReturnMessage(message);
        return responseDto;
    }
}
