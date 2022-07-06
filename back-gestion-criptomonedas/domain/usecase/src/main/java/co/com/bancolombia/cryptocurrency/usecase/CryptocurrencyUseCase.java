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
        return cryptocurrencyRepository.findAll();
    }

    public List<Cryptocurrency> getCryptocurrenciesByCountry(Integer countryId){
        return cryptocurrencyRepository.findByCountry(countryId);
    }

    public List<Cryptocurrency> getCryptocurrenciesByCustomer(Long customerId){
        return cryptocurrencyRepository.findByCustomer(customerId);
    }

    public Cryptocurrency getByIdAndCountryId(Integer cryptoId, Integer countryId){
        return cryptocurrencyRepository
                .findByIdAndCountryId(cryptoId, countryId)
                .orElseThrow(() -> new DomainException(DomainExceptionEnum.CRYPTO_CURRENCY_INVALID_FOR_THE_COUNTRY));
    }

}
