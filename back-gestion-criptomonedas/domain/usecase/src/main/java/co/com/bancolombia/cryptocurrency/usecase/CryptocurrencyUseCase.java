package co.com.bancolombia.cryptocurrency.usecase;

import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.cryptocurrency.model.CryptocurrencyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CryptocurrencyUseCase {

    private final CryptocurrencyRepository cryptocurrencyRepository;

    public List<Cryptocurrency> getCryptocurrencies(){
        List<Cryptocurrency> cryptocurrencies= cryptocurrencyRepository.findAll();
        if(cryptocurrencies.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_CRYPTOCURRENCIES_EXCEPTION);
        }
        return  cryptocurrencies;
    }

    public List<Cryptocurrency> getCryptocurrenciesByCountry(Integer countryId){
        List<Cryptocurrency> cryptocurrencies = cryptocurrencyRepository.findByCountry(countryId);
        if(cryptocurrencies.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_CRYPTOCURRENCIES_EXCEPTION);
        }
        return  cryptocurrencies;
    }

    public List<Cryptocurrency> getCryptocurrenciesByCustomer(Long customerId){
        List<Cryptocurrency> cryptocurrencies = cryptocurrencyRepository.findByCustomer(customerId);
        if(cryptocurrencies.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_CRYPTOCURRENCIES_EXCEPTION);
        }
        return cryptocurrencies;
    }

    public Cryptocurrency getByIdAndCountryId(Integer cryptoId, Integer countryId){
        return cryptocurrencyRepository
                .findByIdAndCountryId(cryptoId, countryId)
                .orElseThrow(() -> new DomainException(DomainExceptionEnum.CRYPTO_CURRENCY_INVALID_FOR_THE_COUNTRY));
    }

}
