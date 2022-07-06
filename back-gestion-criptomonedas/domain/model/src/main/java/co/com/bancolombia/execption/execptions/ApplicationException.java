package co.com.bancolombia.execption.execptions;


import co.com.bancolombia.execption.enums.ApplicationExceptionEnum;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final ApplicationExceptionEnum exception;

    public ApplicationException(ApplicationExceptionEnum exception){
            super(exception.getMessage());
            this.exception = exception;
        }
}