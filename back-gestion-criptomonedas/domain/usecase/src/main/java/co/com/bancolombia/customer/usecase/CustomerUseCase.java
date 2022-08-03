package co.com.bancolombia.customer.usecase;

import co.com.bancolombia.customer.view.CustomerView;
import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.model.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerUseCase {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id){
        return customerRepository.getCustomerById(id)
                .orElseThrow(() ->  new DomainException(DomainExceptionEnum.NOT_FOUND_CUSTOMER_EXCEPTION));
    }

    public Customer addCryptocurrency(Customer customer, Cryptocurrency cryptocurrency){
        boolean isPresent = customer.getCryptocurrencies().stream()
                .anyMatch(c ->  c.getId().equals(cryptocurrency.getId()));

        if(isPresent){
            throw new DomainException(DomainExceptionEnum.CRYPTO_CURRENCY_ALREADY_EXIST);
        }

        customer.getCryptocurrencies().add(cryptocurrency);

        return customerRepository.save(customer);
    }

    public Customer removeCryptocurrency(Customer customer, Cryptocurrency cryptocurrency){
        customer.getCryptocurrencies()
                .stream()
                .filter(c -> c.getId().equals(cryptocurrency.getId()))
                .findFirst()
                .orElseThrow(() -> new DomainException(DomainExceptionEnum.CRYPTO_CURRENCY_NOT_EXIST));

        customer.getCryptocurrencies().remove(cryptocurrency);

        return customerRepository.save(customer);
    }

    public List<CustomerView> getCustomers(){
        List<CustomerView> customers = customerRepository.getCustomers();
        if(customers.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_CUSTOMERS_EXCEPTION);
        }
        return customerRepository.getCustomers();
    }
    public List<CustomerView> getCustomerByCountry(Integer id){
        List<CustomerView> customers = customerRepository.getCustomerByCountry(id);;
        if(customers.isEmpty()){
            throw new DomainException(DomainExceptionEnum.NOT_FOUND_CUSTOMERS_EXCEPTION);
        }
        return customers;
    }

}
