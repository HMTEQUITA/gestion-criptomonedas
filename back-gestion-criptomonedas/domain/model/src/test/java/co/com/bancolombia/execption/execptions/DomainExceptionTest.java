package co.com.bancolombia.execption.execptions;

import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.enums.ExceptionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DomainExceptionTest {
    DomainException domainException;

    @Test
    void applicationExceptionTest(){
        domainException = new DomainException(DomainExceptionEnum.NOT_FOUND_COUNTRY_EXCEPTION);

        Assertions.assertEquals(ExceptionTypeEnum.DOMAIN, domainException.getException().getType());
    }
}
