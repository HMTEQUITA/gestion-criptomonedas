package co.com.bancolombia.execption.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ApplicationExceptionEnumTest {

    ApplicationExceptionEnum[] applicationExceptionEnums;

    @BeforeEach
    void setup(){
        applicationExceptionEnums = new ApplicationExceptionEnum[]{
                ApplicationExceptionEnum.UNAUTHORIZED,
                ApplicationExceptionEnum.BAD_REQUEST,
                ApplicationExceptionEnum.FORBIDDEN,
                ApplicationExceptionEnum.UNSUPPORTED_MEDIA_TYPE,
                ApplicationExceptionEnum.METHOD_NOT_SUPPORTED,
                ApplicationExceptionEnum.MEDIA_TYPE_NOT_ACCEPTABLE,
                ApplicationExceptionEnum.MISSING_PATH_VARIABLE,
                ApplicationExceptionEnum.REQUEST_INVALID_PARAMS,
                ApplicationExceptionEnum.NOT_FOUND
        };
    }

    @Test
    void applicationExceptionEnum(){
        Assertions.assertArrayEquals(applicationExceptionEnums, ApplicationExceptionEnum.values());
    }
}