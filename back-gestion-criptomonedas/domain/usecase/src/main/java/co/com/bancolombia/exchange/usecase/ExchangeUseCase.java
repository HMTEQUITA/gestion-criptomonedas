package co.com.bancolombia.exchange.usecase;

import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.exchange.model.ExchangeRepository;
import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.execptions.DomainException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExchangeUseCase {

    private final ExchangeRepository repository;

    public List<Exchange> getExchanges(){
        List<Exchange> exchanges = repository.findAll();
        if(exchanges.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_EXCHANGES_EXCEPTION);
        }

        return exchanges;
    }
    public List<Exchange> getExchangeByCountry(Integer id){
        List<Exchange> exchanges = repository.findByCountry(id);
        if(exchanges.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_EXCHANGES_EXCEPTION);
        }
        return exchanges;
    }
}
