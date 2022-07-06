package co.com.bancolombia.execption.execptions;

import co.com.bancolombia.execption.enums.ApplicationExceptionEnum;
import co.com.bancolombia.execption.enums.ExceptionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationExceptionTest {
    ApplicationException applicationException;

    @Test
    void applicationExceptionTest(){
        applicationException = new ApplicationException(ApplicationExceptionEnum.BAD_REQUEST);

        Assertions.assertEquals(ExceptionTypeEnum.APPLICATION, applicationException.getException().getType());
    }
}