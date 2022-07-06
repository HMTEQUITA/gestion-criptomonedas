package co.com.bancolombia.countrry.usecase;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.model.CountryRepository;
import co.com.bancolombia.execption.execptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class CountryUseCaseTest {
    @InjectMocks
    private CountryUseCase countryUseCase;

    @Mock
    private CountryRepository countryRepository;


    @Test
    void getCountryByNameTest(){
        Optional<Country> country = Optional.ofNullable(Country.builder().build());
        Mockito.when(countryRepository.findCountryByName(Mockito.anyString())).thenReturn(country);
        Assertions.assertNotNull(countryUseCase.getCountryByName(Mockito.anyString()));
    }

    @Test
    void getCountryByNameExceptionTest(){
        Optional<Country> country = Optional.empty();
        Mockito.when(countryRepository.findCountryByName(Mockito.anyString())).thenReturn(country);
        Assertions.assertThrows(DomainException.class,
                () -> countryUseCase.getCountryByName(Mockito.anyString()));
    }

    @Test
    void getCountriesTest(){
        List<Country> country = List.of(Country.builder().build());
        Mockito.when(countryRepository.getCountries()).thenReturn(country);
        Assertions.assertNotNull(countryUseCase.getCountries());
    }

}