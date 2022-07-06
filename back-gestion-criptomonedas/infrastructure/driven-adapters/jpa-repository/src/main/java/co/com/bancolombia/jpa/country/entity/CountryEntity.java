package co.com.bancolombia.jpa.country.entity;

import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries", schema = "management")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false )
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="country_cryptocurrencies", schema = "management",  joinColumns= @JoinColumn(name="country_id"),
            inverseJoinColumns=@JoinColumn(name="cryptocurrency_id"))
    private Set<CryptocurrencyEntity> cryptocurrencies;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="country_exchange", schema = "management",  joinColumns= @JoinColumn(name="country_id"),
            inverseJoinColumns=@JoinColumn(name="exchange_id"))
    private Set<ExchangeEntity> exchanges;
}