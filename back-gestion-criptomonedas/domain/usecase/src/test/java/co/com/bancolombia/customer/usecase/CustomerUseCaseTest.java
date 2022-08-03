package co.com.bancolombia.customer.usecase;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.model.CustomerRepository;
import co.com.bancolombia.customer.view.CustomerView;
import co.com.bancolombia.execption.execptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
class CustomerUseCaseTest {

    @InjectMocks
    private CustomerUseCase customerUseCase;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getCustomerByIdTest(){
        Optional<Customer> customer = Optional.ofNullable(Customer.builder().build());
        Mockito.when(customerRepository.getCustomerById(1L)).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.getCustomerById(1L));
    }

    @Test
    void getCustomerByIdExceptionTest(){
        Optional<Customer> customer = Optional.empty();
        Mockito.when(customerRepository.getCustomerById(1L)).thenReturn(customer);
        Assertions.assertThrows(DomainException.class,
                () -> customerUseCase.getCustomerById(1L));
    }

    @Test
    void addCryptocurrencyTest(){
        Customer customer = Customer.builder().build();
        Cryptocurrency cryptocurrency = this.cryptocurrency();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.addCryptocurrency(customer, cryptocurrency));
    }

    @Test
    void addCryptocurrencyExceptionTest(){
        Customer customer = this.customer();
        Cryptocurrency cryptocurrency = this.cryptocurrency();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertThrows(DomainException.class,
                () -> customerUseCase.addCryptocurrency(customer, cryptocurrency));
    }

    @Test
    void removeCryptocurrencyTest(){
        Customer customer = this.customer();
        Cryptocurrency cryptocurrency = this.cryptocurrency();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.removeCryptocurrency(customer, cryptocurrency));
    }

    @Test
    void removeCryptocurrencyExceptionTest(){
        Customer customer = Customer.builder().build();
        Cryptocurrency cryptocurrency = this.cryptocurrency();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertThrows(DomainException.class,
                () -> customerUseCase.removeCryptocurrency(customer, cryptocurrency));
    }

    @Test
    void getCustomersTest(){
        List<CustomerView> customer = List.of(CustomerView.builder().build());
        Mockito.when(customerRepository.getCustomers()).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.getCustomers());
    }

    @Test
    void getCustomersExceptionTest(){
        List<CustomerView> customer = List.of();
        Mockito.when(customerRepository.getCustomers()).thenReturn(customer);
        Assertions.assertThrows(DomainException.class,
                () -> customerUseCase.getCustomers());
    }


    @Test
    void getCustomerByCountryTest(){
        List<CustomerView> customer = List.of(CustomerView.builder().build());
        Mockito.when(customerRepository.getCustomerByCountry(1)).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.getCustomerByCountry(1));
    }

    @Test
    void getCustomerByCountryExceptionTest(){
        List<CustomerView> customer = List.of();
        Mockito.when(customerRepository.getCustomerByCountry(1)).thenReturn(customer);
        Assertions.assertThrows(DomainException.class,
                () -> customerUseCase.getCustomerByCountry(1));
    }


    private Customer customer(){
        Set<Cryptocurrency> cryptocurrency = new HashSet<>();
        cryptocurrency.add(this.cryptocurrency());
        return Customer.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .country(Country.builder().build())
                .cryptocurrencies(cryptocurrency)
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