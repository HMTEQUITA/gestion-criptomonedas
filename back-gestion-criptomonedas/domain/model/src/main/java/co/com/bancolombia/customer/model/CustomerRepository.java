package co.com.bancolombia.customer.model;

import co.com.bancolombia.customer.view.CustomerView;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> getCustomerById(Long id);
    Customer save(Customer customer);

    List<CustomerView> getCustomerByCountry(Integer id);

    List<CustomerView> getCustomers();
}
