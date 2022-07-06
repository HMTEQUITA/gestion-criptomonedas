package co.com.bancolombia.jpa.cryptocurrency.repository;

import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyRepositoryAdapter extends
        CrudRepository<CryptocurrencyEntity, Integer>, QueryByExampleExecutor<CryptocurrencyEntity> {
/*
    @Query(value = "select c.id, c.symbol, c.name, c.exchange_rate from management.cryptocurrencies c " +
            " inner join management.country_cryptocurrencies cc" +
            " on c.id = cc.cryptocurrency_id  where cc.country_id = ?1", nativeQuery = true)
    List<CryptocurrencyEntity> findByCountryId(Integer id);
*/
/*
    @Query(value = "select c.id, c.symbol, c.name, c.exchange_rate from management.cryptocurrencies c" +
            " inner join management.users_cryptocurrencies uc" +
            " on c.id = uc.cryptocurrency_id where uc.user_id = ?1", nativeQuery = true)
    List<CryptocurrencyEntity> findByUserId(Long id);
    */

    List<CryptocurrencyEntity> findByCountryId(Integer id);
    List<CryptocurrencyEntity> findByCustomerId(Long id);

    Optional<CryptocurrencyEntity> findByIdAndCountryId(Integer cryptoId, Integer countryId);
}
