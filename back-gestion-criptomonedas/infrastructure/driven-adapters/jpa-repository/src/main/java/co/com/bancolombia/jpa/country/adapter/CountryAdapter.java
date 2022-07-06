package co.com.bancolombia.jpa.country.adapter;

import co.com.bancolombia.country.view.CountryView;
import co.com.bancolombia.jpa.country.mapper.CountryTransformer;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.model.CountryRepository;
import co.com.bancolombia.jpa.country.repository.CountryRepositoryAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Repository
@AllArgsConstructor
public class CountryAdapter implements CountryRepository {

    private final CountryRepositoryAdapter repository;

    @Override
    public Optional<Country> findCountryByName(String name) {
        return repository.findByNameIgnoreCase(name).map(CountryTransformer::ToDto);
    }

    @Override
    public Optional<Country> findCountryById(Integer id) {
        return repository.findById(id).map(CountryTransformer::ToDto);
    }

    @Override
    public List<Country> getCountries() {
        return stream(repository.findAll().spliterator(), false)
                .map(CountryTransformer::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CountryView> getViewCountries() {
        return stream(repository.findAll().spliterator(), false)
                .map(CountryTransformer::toView)
                .collect(Collectors.toList());
    }
}