package co.com.bancolombia.jpa.exchange.adapter;

import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.exchange.model.ExchangeRepository;
import co.com.bancolombia.jpa.country.mapper.CountryTransformer;
import co.com.bancolombia.jpa.exchange.mapper.ExchangeTransformer;
import co.com.bancolombia.jpa.exchange.repository.ExchangeRepositoryAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Repository
@AllArgsConstructor
public class ExchangeAdapter implements ExchangeRepository {

    private final ExchangeRepositoryAdapter repository;

    @Override
    public List<Exchange> findByCountry(Integer id) {
        return repository.findByCountryId(id).stream()
                .map(ExchangeTransformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exchange> findAll() {
        return stream(repository.findAll().spliterator(), false)
                .map(ExchangeTransformer::toDto)
                .collect(Collectors.toList());
    }
}
