package co.com.bancolombia.jpa.customer.adapter;

import co.com.bancolombia.customer.view.CustomerView;
import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import co.com.bancolombia.jpa.customer.mapper.CustomerTransformer;
import co.com.bancolombia.jpa.customer.repository.CustomerRepositoryAdapter;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.model.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Repository
@AllArgsConstructor
public class CustomerAdapter implements CustomerRepository {

    private final CustomerRepositoryAdapter repository;

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return repository.findById(id)
                .map(CustomerTransformer::toDto)                ;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerTransformer.toEntity(customer);
        return CustomerTransformer.toDto(repository.save(customerEntity));
    }

    @Override
    public List<CustomerView> getCustomers() {
        return stream(repository.findAll().spliterator(), false)
                .map(CustomerTransformer::toView)
                .collect(Collectors.toList());
    }
    @Override
    public List<CustomerView> getCustomerByCountry(Integer id) {
            return repository.findByCountryId(id)
                .stream()
                .map(CustomerTransformer::toView)
                .collect(Collectors.toList());
    }
}