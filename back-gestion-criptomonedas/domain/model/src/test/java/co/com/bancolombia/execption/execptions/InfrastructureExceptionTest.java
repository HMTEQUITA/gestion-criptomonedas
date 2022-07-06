package co.com.bancolombia.execption.execptions;

import co.com.bancolombia.execption.enums.ExceptionTypeEnum;
import co.com.bancolombia.execption.enums.InfrastructureExceptionEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InfrastructureExceptionTest {
    InfrastructureException infrastructureException;

    @Test
    void applicationExceptionTest(){
        infrastructureException = new InfrastructureException(InfrastructureExceptionEnum.RESPONSE_TIME_OUT);

        Assertions.assertEquals(ExceptionTypeEnum.INFRASTRUCTURE, infrastructureException.getException().getType());
    }
}