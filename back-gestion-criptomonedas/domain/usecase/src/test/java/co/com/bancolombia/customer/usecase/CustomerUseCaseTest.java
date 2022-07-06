package co.com.bancolombia.customer.usecase;

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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void updateCryptocurrencyTest(){
        Customer customer = Customer.builder().build();
        Cryptocurrency cryptocurrency = Cryptocurrency.builder().build();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.updateCryptocurrency(customer, cryptocurrency));
    }

    @Test
    void getCustomersTest(){
        List<CustomerView> customer = List.of(CustomerView.builder().build());
        Mockito.when(customerRepository.getCustomers()).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.getCustomers());
    }

    @Test
    void getCustomerByCountryTest(){
        List<CustomerView> customer = List.of(CustomerView.builder().build());
        Mockito.when(customerRepository.getCustomerByCountry(1)).thenReturn(customer);
        Assertions.assertNotNull(customerUseCase.getCustomerByCountry(1));
    }

}