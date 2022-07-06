package co.com.bancolombia.execption.enums;

import co.com.bancolombia.execption.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfrastructureExceptionEnum implements IExceptionEnum {

    UNEXPECTED_ERROR("Unexpected error"),
    RESPONSE_TIME_OUT("Waiting time expired"),
    ;
    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.INFRASTRUCTURE;
    private final String message;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}
