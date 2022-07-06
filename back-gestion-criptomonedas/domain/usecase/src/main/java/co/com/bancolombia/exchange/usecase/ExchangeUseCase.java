package co.com.bancolombia.exchange.usecase;

import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.exchange.model.ExchangeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExchangeUseCase {

    private final ExchangeRepository repository;

    public List<Exchange> getExchanges(){
        return  repository.findAll();
    }
    public List<Exchange> getExchangeByCountry(Integer id){
        return  repository.findByCountry(id);
    }
}
