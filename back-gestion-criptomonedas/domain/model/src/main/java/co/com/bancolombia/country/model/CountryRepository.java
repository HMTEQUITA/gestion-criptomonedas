package co.com.bancolombia.country.model;

import co.com.bancolombia.country.view.CountryView;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    Optional<Country> findCountryByName(String name);
    Optional<Country> findCountryById(Integer id);
    List<Country> getCountries();

    List<CountryView> getViewCountries();
}
