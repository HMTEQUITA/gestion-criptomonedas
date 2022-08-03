package co.com.bancolombia.jpa.user.adapter;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import co.com.bancolombia.jpa.user.entity.RoleEntity;
import co.com.bancolombia.jpa.user.entity.UserEntity;
import co.com.bancolombia.jpa.user.repository.UserRepositoryAdapter;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserAdapterTest {

    @InjectMocks
    UserAdapter userAdapter;

    @Mock
    UserRepositoryAdapter userRepositoryAdapter;

    @Test
    void findByUserNameTest() {
        Optional<UserEntity> user = Optional.of(this.userEntity()) ;

        Mockito.when(userRepositoryAdapter.findByEmail(Mockito.anyString()))
                .thenReturn(user);

        Assertions.assertNotNull(userAdapter.findByUserName(Mockito.anyString()));
    }

    @Test
    void save() {
        Mockito.when(userRepositoryAdapter.save(Mockito.any()))
                .thenReturn(this.userEntity());

        Assertions.assertNotNull(userAdapter.save(this.user()));
    }

    private User user(){
        return User.builder()
                .id(1L)
                .email("email")
                .password("password")
                .roles(Set.of(this.role()))
                .customer(this.customer())
                .build();
    }
    private Role role(){
        return Role.builder()
                .id(1)
                .name("name")
                .build();
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

    private UserEntity userEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("email");
        userEntity.setCustomer(this.customerEntity());
        userEntity.setRoles(Set.of(this.roleEntity()));
        return userEntity;
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
    private RoleEntity roleEntity(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        roleEntity.setName("name");
        return roleEntity;
    }
}