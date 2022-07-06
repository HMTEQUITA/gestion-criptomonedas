package co.com.bancolombia.jpa.exchange.repository;

import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ExchangeRepositoryAdapter extends CrudRepository<ExchangeEntity, Integer>,
        QueryByExampleExecutor<ExchangeEntity>{

    List<ExchangeEntity> findByCountryId(Integer id);
}
