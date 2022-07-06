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

    public Customer updateCryptocurrency(Customer customer, Cryptocurrency cryptocurrency){
        customer.getCryptocurrencies().add(cryptocurrency);
        return customerRepository.save(customer);
    }


    public List<CustomerView> getCustomers(){
        return customerRepository.getCustomers();
    }
    public List<CustomerView> getCustomerByCountry(Integer id){
        return customerRepository.getCustomerByCountry(id);
    }

}
