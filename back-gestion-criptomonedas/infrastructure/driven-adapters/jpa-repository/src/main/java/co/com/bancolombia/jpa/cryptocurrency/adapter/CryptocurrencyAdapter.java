package co.com.bancolombia.jpa.cryptocurrency.adapter;

import co.com.bancolombia.jpa.cryptocurrency.mapper.CryptoTransformer;
import co.com.bancolombia.jpa.cryptocurrency.repository.CryptocurrencyRepositoryAdapter;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.cryptocurrency.model.CryptocurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Repository
@AllArgsConstructor
public class CryptocurrencyAdapter implements CryptocurrencyRepository {

    private final CryptocurrencyRepositoryAdapter repository;

    @Override
    public List<Cryptocurrency> findAll() {
        return stream(repository.findAll().spliterator(), false)
                .map(CryptoTransformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cryptocurrency> findByCountry(Integer countryId) {
        return repository.findByCountryId(countryId)
                .stream()
                .map(CryptoTransformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cryptocurrency> findByCustomer(Long customerId) {
        return  repository.findByCustomerId(customerId).stream()
                .map(CryptoTransformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cryptocurrency> findByIdAndCountryId(Integer cryptoId, Integer countryId) {
        return repository.findByIdAndCountryId(cryptoId, countryId)
                .map(CryptoTransformer::toDto);
    }
}