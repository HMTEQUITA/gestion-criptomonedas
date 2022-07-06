package co.com.bancolombia.exchange.model;

import java.util.List;

public interface ExchangeRepository {
    List<Exchange> findByCountry(Integer id);
    List<Exchange> findAll();

}
