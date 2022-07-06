package co.com.bancolombia.execption.interfaces;

import co.com.bancolombia.execption.enums.ExceptionTypeEnum;

public interface IExceptionEnum {
    String name();
    String getMessage();
    ExceptionTypeEnum getType();
}