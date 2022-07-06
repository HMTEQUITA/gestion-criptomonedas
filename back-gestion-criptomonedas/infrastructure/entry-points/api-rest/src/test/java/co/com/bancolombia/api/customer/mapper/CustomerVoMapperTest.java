package co.com.bancolombia.api.customer.mapper;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.api.customer.dto.CustomerVO;
import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.view.CustomerView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerVoMapperTest {

    private Customer customer;
    private CustomerVO customerVO;
    private CustomerViewDTO customerViewDTO;
    private CustomerView customerView;

    @BeforeEach
    void setup(){
        customer = Customer.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .country(Country.builder()
                        .id(1)
                        .name("country")
                        .build())
                .cryptocurrencies(Set.of(Cryptocurrency.builder().build()))
                .build();

        customerVO = CustomerVO.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .country("country")
                .cryptocurrencies(Set.of(CryptocurrencyVO.builder().build()))
                .build();


        customerViewDTO = CustomerViewDTO.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .build();

        customerView = CustomerView.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .build();
    }


    @Test
    void toDto() {
        Customer result = CustomerVoMapper.toDto(customerVO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(customer.getId(), result.getId()),
                () -> Assertions.assertEquals(customer.getName(), result.getName()),
                () -> Assertions.assertEquals(customer.getSurname(), result.getSurname()),
                () -> Assertions.assertEquals(customer.getCountry().getName(), result.getCountry().getName()),
                () -> Assertions.assertEquals(customer.getCryptocurrencies().size(), result.getCryptocurrencies().size())
        );
    }

    @Test
    void toVo() {
        CustomerVO result = CustomerVoMapper.toVo(customer);

        Assertions.assertAll(
                () -> Assertions.assertEquals(customerVO.getId(), result.getId()),
                () -> Assertions.assertEquals(customerVO.getName(), result.getName()),
                () -> Assertions.assertEquals(customerVO.getSurname(), result.getSurname()),
                () -> Assertions.assertEquals(customerVO.getCountry(), result.getCountry()),
                () -> Assertions.assertEquals(customerVO.getCryptocurrencies().size(), result.getCryptocurrencies().size())
        );
    }

    @Test
    void toView() {
        CustomerViewDTO result = CustomerVoMapper.toView(customerView);

        Assertions.assertAll(
                () -> Assertions.assertEquals(customerView.getId(), result.getId()),
                () -> Assertions.assertEquals(customerView.getName(), result.getName()),
                () -> Assertions.assertEquals(customerView.getSurname(), result.getSurname())

        );

    }
}