package co.com.bancolombia.execption.enums;

import co.com.bancolombia.execption.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionEnum implements IExceptionEnum {
    NOT_FOUND_COUNTRY_EXCEPTION("Pais no encontrado."),
    NOT_FOUND_COUNTRIES_EXCEPTION("No se encontro ningun Pais."),
    NOT_FOUND_CRYPTOCURRENCIES_EXCEPTION("No se encontro ninguna criptomoneda."),
    CRYPTO_CURRENCY_INVALID_FOR_THE_COUNTRY("Criptomoneda no permitida para el pais."),
    CRYPTO_CURRENCY_ALREADY_EXIST("Criptomoneda ya existe para el cliente."),
    CRYPTO_CURRENCY_NOT_EXIST("Criptomoneda no existe para el cliente."),
    NOT_FOUND_CUSTOMER_EXCEPTION("Cliente no encontrado."),
    NOT_FOUND_CUSTOMERS_EXCEPTION("No se encontro ningun cliente."),
    NOT_FOUND_EXCHANGES_EXCEPTION("No se encontro ninuna plataforma de intercambio"),
    //NOT_FOUND_USER_EXCEPTION("User not found"),
    BAD_CREDENTIALS_EXCEPTION("Credenciales erroneas."),
    USER_ALREADY_EXISTS_EXCEPTION("Usuario ya existe."),
    NOT_FOUND_ROLE_EXCEPTION("Rol no encontrado."),
    //CRYPTO_CURRENCY_INVALID_FOR_THE_USER("Cryptocurrency invalid for the user"),
    ;

    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.DOMAIN;
    private final String message;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}
