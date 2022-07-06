package co.com.bancolombia.cryptocurrency.model;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyRepository {
    List<Cryptocurrency> findAll();
    List<Cryptocurrency> findByCountry(Integer id);
    List<Cryptocurrency> findByCustomer(Long id);
    Optional<Cryptocurrency> findByIdAndCountryId(Integer cryptoId, Integer countryId);
}
