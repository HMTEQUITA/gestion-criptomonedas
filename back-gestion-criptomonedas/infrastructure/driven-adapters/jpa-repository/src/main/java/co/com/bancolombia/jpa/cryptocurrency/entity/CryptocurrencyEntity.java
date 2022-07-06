package co.com.bancolombia.jpa.cryptocurrency.entity;

import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cryptocurrencies", schema = "management")
public class CryptocurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "name")
    private String name;
    @Column(name = "exchange_rate")
    private double exchangeRate;
    @ManyToMany(mappedBy = "cryptocurrencies", fetch = FetchType.LAZY)
    private List<CustomerEntity> customer;
    @ManyToMany(mappedBy = "cryptocurrencies", fetch = FetchType.LAZY)
    private List<CountryEntity> country;
}
