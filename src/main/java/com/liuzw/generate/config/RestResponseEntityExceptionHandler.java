package com.liuzw.generate.config;


import com.liuzw.generate.bean.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

/**
 * 全局异常异常处理器.
 *
 * @author liuzw
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(final Exception ex,
            final WebRequest request) {
        log.error("500 Status Code", ex);
        final ResultData<?> apiError = message(ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class,
            IllegalStateException.class})
    public ResponseEntity<Object> handle500s(final RuntimeException ex, final WebRequest request) {
        log.error("500 Status Code", ex);
        final ResultData<?> apiError = message(ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationException(Exception e, final WebRequest request) {
        log.error("500 Status Code", e);
        final ResultData<?> apiError = message(e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(
            final HttpMessageNotReadableException ex, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        log.error("handleHttpMessageNotReadable Bad Request: {}", ex.getMessage());
        log.debug("handleHttpMessageNotReadable Bad Request: ", ex);

        final ResultData<?> apiError = message(ex);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleEverything(final AccessDeniedException ex,
            final WebRequest request) {
        log.error("403 Status Code", ex);

        final ResultData<?> apiError = message(ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN,
                request);
    }


    private ResultData<?> message(final Exception ex) {
        final String message =
                ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        return ResultData.createErrorResult(message);
    }

    private ResultData<?> message(final RuntimeException ex) {
        final String message =
                ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        return ResultData.createErrorResult(message);
    }
}
