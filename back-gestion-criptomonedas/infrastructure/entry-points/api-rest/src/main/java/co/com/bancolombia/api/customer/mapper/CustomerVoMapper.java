package co.com.bancolombia.api.customer.mapper;

import co.com.bancolombia.api.cryptocurrency.mapper.CryptoVoMapper;
import co.com.bancolombia.api.customer.dto.CustomerVO;
import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.view.CustomerView;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
public final class CustomerVoMapper {

    public static Customer toDto(CustomerVO customerVo) {
        return Customer.builder()
                .id(customerVo.getId())
                .name(customerVo.getName())
                .surname(customerVo.getSurname())
                .country(Country.builder()
                        .name(customerVo.getCountry())
                        .build())
                .cryptocurrencies(customerVo.getCryptocurrencies()
                        .stream()
                        .map(CryptoVoMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CustomerVO toVo(Customer customer) {
        return CustomerVO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .country(customer.getCountry().getName())
                .cryptocurrencies(customer.getCryptocurrencies()
                        .stream()
                        .map(CryptoVoMapper::toVO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CustomerViewDTO toView(CustomerView customerView){
        return CustomerViewDTO.builder()
                .id(customerView.getId())
                .name(customerView.getName())
                .surname(customerView.getSurname())
                .country(customerView.getCountry())
                .build();
    }
}