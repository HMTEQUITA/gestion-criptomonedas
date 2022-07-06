package co.com.bancolombia.jpa.customer.repository;

import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface CustomerRepositoryAdapter extends CrudRepository<CustomerEntity, Long>,
        QueryByExampleExecutor<CustomerEntity> {

    List<CustomerEntity> findByCountryId(Integer id);
}
