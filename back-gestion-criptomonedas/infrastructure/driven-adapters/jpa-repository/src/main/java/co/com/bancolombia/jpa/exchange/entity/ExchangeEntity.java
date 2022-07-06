package co.com.bancolombia.jpa.exchange.entity;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.jpa.country.entity.CountryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exchange", schema = "management")
public class ExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false )
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private boolean status;
    @ManyToMany(mappedBy = "exchanges", fetch = FetchType.LAZY)
    private List<CountryEntity> country;
}
