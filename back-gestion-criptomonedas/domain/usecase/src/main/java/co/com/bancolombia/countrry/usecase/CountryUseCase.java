package co.com.bancolombia.countrry.usecase;

import co.com.bancolombia.country.view.CountryView;
import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.model.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CountryUseCase {

    private final CountryRepository countryRepository;

    public Country getCountryByName(String name){
        return countryRepository.findCountryByName(name)
                .orElseThrow(() -> new DomainException(DomainExceptionEnum.NOT_FOUND_COUNTRY_EXCEPTION));
    }

    public List<CountryView> getCountries(){
        List<CountryView> countries = countryRepository.getViewCountries();
        if (countries.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_COUNTRIES_EXCEPTION);
        }
        return countries;
    }
}
