package co.com.bancolombia.jpa.customer.mapper;

import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.view.CustomerView;
import co.com.bancolombia.jpa.country.mapper.CountryTransformer;
import co.com.bancolombia.jpa.cryptocurrency.mapper.CryptoTransformer;
import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
public final class CustomerTransformer {

    public static Customer toDto(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .surname(customerEntity.getSurname())
                .country(CountryTransformer.ToDto(customerEntity.getCountry()))
                .cryptocurrencies(customerEntity.getCryptocurrencies()
                        .stream()
                        .map(CryptoTransformer::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setSurname(customer.getSurname());
        customerEntity.setCountry(CountryTransformer.toEntity(customer.getCountry()));
        customerEntity.setCryptocurrencies(customer.getCryptocurrencies()
                .stream()
                .map(CryptoTransformer::toEntity)
                .collect(Collectors.toSet())
        );

        return customerEntity;
    }

    public static CustomerView toView(CustomerEntity customerEntity){
        return CustomerView.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .surname(customerEntity.getSurname())
                .country(customerEntity.getCountry().getName())
                .build();
    }
}