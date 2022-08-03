package co.com.bancolombia.jpa.customer.adapter;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import co.com.bancolombia.jpa.customer.repository.CustomerRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
class CustomerAdapterTest {

    @InjectMocks
    private CustomerAdapter customerAdapter;

    @Mock
    private CustomerRepositoryAdapter customerRepositoryAdapter;


    @BeforeEach
    void setup(){

    }



    @Test
    void getCustomerById() {
        CustomerEntity customerEntities = customerEntity();
        Mockito.when(customerRepositoryAdapter.findById(1L)).thenReturn(Optional.of(customerEntities));
        Assertions.assertNotNull(customerAdapter.getCustomerById(1L));
    }

    @Test
    void getCustomers() {
        List<CustomerEntity> customerEntities = List.of(this.customerEntity());
        Mockito.when(customerRepositoryAdapter.findAll()).thenReturn(customerEntities);
        Assertions.assertNotNull(customerAdapter.getCustomers());
    }

    @Test
    void getCustomerByCountry() {
        List<CustomerEntity> customerEntities = List.of(this.customerEntity());
        Mockito.when(customerRepositoryAdapter.findByCountryId(1)).thenReturn(customerEntities);
        Assertions.assertNotNull(customerAdapter.getCustomerByCountry(1));

    }

    @Test
    void save() {
        Customer customer = this.customer();
        CustomerEntity customerEntities = this.customerEntity();
        Mockito.when(customerRepositoryAdapter.save(Mockito.any())).thenReturn(customerEntities);
        Assertions.assertNotNull(customerAdapter.save(customer));
    }

    private CustomerEntity customerEntity(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("name");
        customerEntity.setSurname("surname");
        customerEntity.setCountry(this.countryEntity());
        customerEntity.setCryptocurrencies(Set.of(this.cryptocurrencyEntity()));
        return customerEntity;
    }

    private CountryEntity countryEntity(){
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        countryEntity.setName("name");
        countryEntity.setCryptocurrencies(Set.of(this.cryptocurrencyEntity()));
        return countryEntity;
    }

    private CryptocurrencyEntity cryptocurrencyEntity(){
        CryptocurrencyEntity cryptocurrencyEntity = new CryptocurrencyEntity();
        cryptocurrencyEntity.setId(1);
        cryptocurrencyEntity.setName("name");
        cryptocurrencyEntity.setSymbol("symbol");
        cryptocurrencyEntity.setExchangeRate(10.0);
        return cryptocurrencyEntity;
    }

    private Customer customer(){
        return Customer.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .country(this.country())
                .cryptocurrencies(Set.of(this.cryptocurrency()))
                .build();
    }
    private Country country(){
        return Country.builder()
                .id(1)
                .name("name")
                .cryptocurrencies(Set.of(this.cryptocurrency()))
                .build();
    }
    private Cryptocurrency cryptocurrency(){
        return Cryptocurrency.builder()
                .id(1)
                .name("name")
                .symbol("symbol")
                .exchangeRate(10.0)
                .build();
    }
}