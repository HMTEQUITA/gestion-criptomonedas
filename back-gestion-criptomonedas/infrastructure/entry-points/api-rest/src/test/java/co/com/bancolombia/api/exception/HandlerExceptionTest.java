package co.com.bancolombia.api.exception;

import co.com.bancolombia.api.securitymodule.dto.CredentialVO;
import co.com.bancolombia.execption.enums.ApplicationExceptionEnum;
import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.enums.InfrastructureExceptionEnum;
import co.com.bancolombia.execption.execptions.ApplicationException;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.execption.execptions.InfrastructureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays.*;
import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.Arrays.asList;
@ExtendWith(SpringExtension.class)
class HandlerExceptionTest {

    @InjectMocks
    HandlerException restExceptionHandler;


    @Test
    void methodNotAAllowedHandler() {
        final var exception = new HttpRequestMethodNotSupportedException("POST", asList("GET", "DELETE"));

        final var actual = restExceptionHandler.handleHttpRequestMethodNotSupported(
                exception, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, requestStub()
        );

        assertNotNull(actual);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), actual.getStatusCodeValue());
    }

    @Test
    void unsupportedMediaTypeHandler() {
        final var exception = new HttpMediaTypeNotSupportedException(
                MediaType.APPLICATION_JSON,
                Collections.singletonList(MediaType.IMAGE_JPEG)
        );

        final var actual = restExceptionHandler.handleHttpMediaTypeNotSupported(
                exception, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, requestStub()
        );

        assertNotNull(actual);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), actual.getStatusCodeValue());
    }

    @Test
    void notAcceptableMediaTypeHandler() {
        final var exception = new HttpMediaTypeNotAcceptableException(
                Collections.singletonList(MediaType.IMAGE_JPEG)
        );

        final var actual = restExceptionHandler.handleHttpMediaTypeNotAcceptable(
                exception, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, requestStub()
        );

        assertNotNull(actual);
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), actual.getStatusCodeValue());
    }

    @Test
    void missingPathVariableHandler() throws NoSuchMethodException {
        var method = getClass().getDeclaredMethod("handle", String.class);
        var parameter = new MethodParameter(method, 0);
        var exception = new MissingPathVariableException("foo", parameter);

        final var actual = restExceptionHandler.handleMissingPathVariable(
                exception, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, requestStub()
        );

        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());

    }

    @Test
    void missingServletRequestParameterHandler() {
        var ex = new MissingServletRequestParameterException("param", "type");

        final var actual = restExceptionHandler.handleMissingServletRequestParameter(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );

        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());
    }

    @Test
    void servletRequestBindingExceptionHandler() {
        var ex = new ServletRequestBindingException("message");
        var actual = restExceptionHandler.handleServletRequestBindingException(
                ex, new HttpHeaders(), HttpStatus.NOT_FOUND, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());
    }

    @Test
    void conversionNotSupportedHandler() {
        var ex = new ConversionNotSupportedException(new Object(), String.class, new Exception());
        var actual = restExceptionHandler.handleConversionNotSupported(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());
    }

    @Test
    void typeMismatchHandler() {
        var ex = new TypeMismatchException("foo", String.class);
        var actual = restExceptionHandler.handleTypeMismatch(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());
    }

    @Test
    void httpMessageNotReadableHandler() {
        var inputMessage = Mockito.mock(HttpInputMessage.class);
        var ex = new HttpMessageNotReadableException("foo", inputMessage);
        var actual = restExceptionHandler.handleHttpMessageNotReadable(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());
    }

    @Test
    void httpMessageNotWritableHandler() {
        var ex = new HttpMessageNotWritableException("foo");
        var actual = restExceptionHandler.handleHttpMessageNotWritable(
                ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());
    }

    @Test
    void methodArgumentNotValidHandler() throws NoSuchMethodException {
        var bindingResult = new MapBindingResult(Collections.singletonMap("a", "b"), "objectName");
        bindingResult.addError(new ObjectError("c", "d"));

        var method = CredentialVO.class.getMethod("getPassword");
        var handlerMethod = new HandlerMethod(new CredentialVO(), method);
        var methodReturnType = handlerMethod.getReturnType();

        var ex = new MethodArgumentNotValidException(methodReturnType, bindingResult);
        var actual = restExceptionHandler.handleMethodArgumentNotValid(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());

    }

    @Test
    void missingServletRequestPartHandler() {
        var ex = new MissingServletRequestPartException("name");
        var actual = restExceptionHandler.handleMissingServletRequestPart(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());

    }

    @Test
    void bindExceptionHandler() {
        var ex = new BindException(new Object(), "name");
        var actual = restExceptionHandler.handleBindException(ex,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), actual.getStatusCodeValue());
    }

    @Test
    void noHandlerFoundExceptionHandler() {
        var req = new ServletServerHttpRequest(new MockHttpServletRequest(HttpMethod.GET.name(), "/resource"));
        var ex = new NoHandlerFoundException(
                HttpMethod.GET.name(), req.getServletRequest().getRequestURI(), req.getHeaders()
        );
        var actual = restExceptionHandler.handleNoHandlerFoundException(
                ex, new HttpHeaders(), HttpStatus.NOT_FOUND, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.NOT_FOUND.value(), actual.getStatusCodeValue());
    }

    @Test
    void asyncRequestTimeoutExceptionHandler() {
        var ex = new AsyncRequestTimeoutException();
        var actual = restExceptionHandler.handleAsyncRequestTimeoutException(
                ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, requestStub()
        );
        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());
    }

    @Test
    void exceptionHandler() {
        final var actual = restExceptionHandler.exceptionErrorHandler(new Exception());

        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());

    }

    @Test
    void handleExceptionInternal() {
        var ex = new NumberFormatException();
        final var actual = restExceptionHandler.handleExceptionInternal(
                ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, requestStub());

        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());

    }

    @Test
    void throwableErrorHandler() {
        final var actual = restExceptionHandler.throwableErrorHandler(new Exception());
        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());
    }

    @Test
    void domainErrorHandler() {
        final var exception = new DomainException(DomainExceptionEnum.NOT_FOUND_COUNTRY_EXCEPTION);

        final var actual = restExceptionHandler.domainErrorHandler(exception);

        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getStatusCodeValue());
    }

    @Test
    void infrastructureErrorHandler() {
        final var exception = new InfrastructureException(InfrastructureExceptionEnum.RESPONSE_TIME_OUT);

        final var actual = restExceptionHandler.infrastructureErrorHandler(exception);

        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());

    }

    @Test
    void applicationErrorHandler() {
        final var exception = new ApplicationException(
                ApplicationExceptionEnum.UNAUTHORIZED
        );
        final var actual = restExceptionHandler.applicationExceptionErrorHandler(exception);
        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getStatusCodeValue());
    }

    void handle(String arg) {
    }

    WebRequest requestStub() {
        return new ServletWebRequest(new MockHttpServletRequest("GET", "/"),
                new MockHttpServletResponse());
    }
}