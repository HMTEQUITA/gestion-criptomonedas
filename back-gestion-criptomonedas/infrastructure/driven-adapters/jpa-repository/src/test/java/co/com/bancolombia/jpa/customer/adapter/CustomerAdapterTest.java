package co.com.bancolombia.jpa.customer.adapter;

import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.jpa.country.adapter.CountryAdapter;
import co.com.bancolombia.jpa.country.repository.CountryRepositoryAdapter;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomerAdapterTest {

    @InjectMocks
    private CustomerAdapter customerAdapter;

    @Mock
    private CustomerRepositoryAdapter customerRepositoryAdapter;


    @BeforeEach
    void setup(){

    }


/*
    @Test
    void getCustomerById() {
        CustomerEntity customerEntities = customerEntity();
        Mockito.when(customerRepositoryAdapter.findById(1L)).thenReturn(Optional.of(customerEntities));
        Assertions.assertNotNull(customerAdapter.getCustomerById(1L));
    }
*/
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
/*
    @Test
    void save() {
        Customer customer = this.customer();
        CustomerEntity customerEntities = this.customerEntity();
        Mockito.when(customerRepositoryAdapter.save(customerEntities)).thenReturn(customerEntities);
        Assertions.assertNotNull(customerAdapter.save(customer));
    }*/
    private CustomerEntity customerEntity(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("name");

        return customerEntity;
    }

    private Customer customer(){
        return Customer.builder()
                .id(1L)
                .name("name")
                .build();
    }
}