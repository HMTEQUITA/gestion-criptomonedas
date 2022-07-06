package co.com.bancolombia.execption.enums;

import co.com.bancolombia.execption.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionEnum implements IExceptionEnum {


    UNAUTHORIZED("UNAUTHORIZED"),
    BAD_REQUEST("BAD_REQUEST"),
    FORBIDDEN("FORBIDDEN"),
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE"),
    METHOD_NOT_SUPPORTED("METHOD_NOT_SUPPORTED"),
    MEDIA_TYPE_NOT_ACCEPTABLE("MEDIA_TYPE_NOT_ACCEPTABLE"),
    MISSING_PATH_VARIABLE("MISSING_PATH_VARIABLE"),
    REQUEST_INVALID_PARAMS("REQUEST_INVALID_PARAMS"),
    NOT_FOUND("NOT_FOUND")
    ;

    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.APPLICATION;
    private final String message;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}
