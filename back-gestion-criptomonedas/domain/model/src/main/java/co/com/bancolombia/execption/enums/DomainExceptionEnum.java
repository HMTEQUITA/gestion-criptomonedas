package co.com.bancolombia.execption.enums;

import co.com.bancolombia.execption.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionEnum implements IExceptionEnum {


    NOT_FOUND_USER_EXCEPTION("User not found"),

    NOT_FOUND_CUSTOMER_EXCEPTION("User not found"),
    NOT_FOUND_COUNTRY_EXCEPTION("Country not found"),
    USER_ALREADY_EXISTS_EXCEPTION("User already exists"),
    NOT_FOUND_ROLE_EXCEPTION("The roll not found"),
    CRYPTO_CURRENCY_INVALID_FOR_THE_USER("Cryptocurrency invalid for the user"),

    CRYPTO_CURRENCY_INVALID_FOR_THE_COUNTRY("Cryptocurrency invalid for the country")
    ;

    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.DOMAIN;
    private final String message;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}
