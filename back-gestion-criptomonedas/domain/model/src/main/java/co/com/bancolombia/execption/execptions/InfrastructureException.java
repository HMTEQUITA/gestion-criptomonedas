package co.com.bancolombia.execption.execptions;

import co.com.bancolombia.execption.enums.InfrastructureExceptionEnum;
import lombok.Getter;

@Getter
public class InfrastructureException extends RuntimeException{
    private final InfrastructureExceptionEnum exception;

    public InfrastructureException(InfrastructureExceptionEnum exception){
        super(exception.getMessage());
        this.exception = exception;
    }
}
