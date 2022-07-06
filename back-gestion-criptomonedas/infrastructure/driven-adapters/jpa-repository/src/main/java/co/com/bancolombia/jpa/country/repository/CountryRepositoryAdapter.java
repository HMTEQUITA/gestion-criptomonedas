package co.com.bancolombia.jpa.country.repository;

import co.com.bancolombia.jpa.country.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface CountryRepositoryAdapter extends CrudRepository<CountryEntity, Integer>,
        QueryByExampleExecutor<CountryEntity> {

    Optional<CountryEntity> findByNameIgnoreCase(String name);

}
