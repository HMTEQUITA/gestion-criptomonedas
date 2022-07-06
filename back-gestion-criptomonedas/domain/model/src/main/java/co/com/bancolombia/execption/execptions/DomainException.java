package co.com.bancolombia.execption.execptions;

import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException{
    private final DomainExceptionEnum exception;

    public DomainException(DomainExceptionEnum exception){
        super(exception.getMessage());
        this.exception = exception;
    }
}
