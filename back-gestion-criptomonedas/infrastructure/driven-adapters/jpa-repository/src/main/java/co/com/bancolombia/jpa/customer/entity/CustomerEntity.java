package co.com.bancolombia.jpa.customer.entity;

import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers", schema = "management")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false )
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToOne()
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private CountryEntity country;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="customer_cryptocurrencies", schema = "management",  joinColumns= @JoinColumn(name="customer_id"),
            inverseJoinColumns=@JoinColumn(name="cryptocurrency_id"))
    private Set<CryptocurrencyEntity> cryptocurrencies;
}