package co.com.bancolombia.api.exception;

import co.com.bancolombia.execption.enums.ApplicationExceptionEnum;
import co.com.bancolombia.execption.enums.InfrastructureExceptionEnum;
import co.com.bancolombia.execption.execptions.ApplicationException;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.execption.execptions.InfrastructureException;
import co.com.bancolombia.execption.interfaces.IExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.METHOD_NOT_SUPPORTED, status, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported
            (HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.UNSUPPORTED_MEDIA_TYPE, status, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable
            (HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.MEDIA_TYPE_NOT_ACCEPTABLE, status, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable
            (MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.MISSING_PATH_VARIABLE, status, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter
            (MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException
            (ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported
            (ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch
            (TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable
            (HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(InfrastructureExceptionEnum.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.REQUEST_INVALID_PARAMS, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart
            (MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleBindException
            (BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.UNPROCESSABLE_ENTITY, ex);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException
            (NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.NOT_FOUND, HttpStatus.NOT_FOUND, ex);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException
            (AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return buildResponseEntity(InfrastructureExceptionEnum.RESPONSE_TIME_OUT, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal
            (Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ApplicationExceptionEnum.BAD_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> domainErrorHandler(DomainException exception){
        return buildResponseEntity(exception.getException(), HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<Object> infrastructureErrorHandler(InfrastructureException exception){
        return buildResponseEntity(exception.getException(), HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> applicationExceptionErrorHandler(ApplicationException exception){
        return buildResponseEntity(exception.getException(), HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionErrorHandler(Exception exception){
        return buildResponseEntity(InfrastructureExceptionEnum.UNEXPECTED_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> throwableErrorHandler(Throwable exception){
        return buildResponseEntity(InfrastructureExceptionEnum.UNEXPECTED_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    private ResponseEntity<Object> buildResponseEntity(IExceptionEnum message, HttpStatus status, Throwable tw){
        log.error("Message: {}", message.getMessage());
        log.error("\t status: {}", status);
        log.error("\t Exception: {}", tw);

        return  ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message.getMessage());
    }
}