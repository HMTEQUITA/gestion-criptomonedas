package co.com.bancolombia.api.customer.controller;

import co.com.bancolombia.api.country.controller.CountryController;
import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.api.customer.dto.CustomerVO;
import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.api.exception.HandlerException;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.customer.usecase.CustomerUseCase;
import co.com.bancolombia.customer.view.CustomerView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountryController.class)
@ContextConfiguration(classes = {CustomerController.class, CustomerControllerTest.class, HandlerException.class})
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerUseCase customerUseCase;

    @MockBean
    private CryptocurrencyUseCase cryptocurrencyUseCase;

    @Test
    void getCustomersTest() throws Exception {
        Mockito.when(customerUseCase.getCustomers())
                .thenReturn(List.of(CustomerView.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .content(objectMapper.writeValueAsString(List.of(CustomerViewDTO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        Long id = 1L;
        Mockito.when(customerUseCase.getCustomerById(id))
                .thenReturn(Customer.builder()
                        .country(Country.builder().name("col").build())
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/"+id)
                        .content(objectMapper.writeValueAsString(CustomerVO.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void customerByCountryTest() throws Exception{
        Integer id = 1;
        Mockito.when(customerUseCase.getCustomerByCountry(id))
                .thenReturn(List.of(CustomerView.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/country/"+id)
                        .content(objectMapper.writeValueAsString(List.of(CustomerViewDTO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addCryptocurrencyTest() throws Exception{
        Long customerId = 1L;
        Integer cryptocurrencyId = 1;

        Customer customer = this.customer();
        Cryptocurrency cryptocurrency = this.cryptocurrency();

        Mockito.when(customerUseCase.getCustomerById(customerId))
                .thenReturn(customer);

        Mockito.when(cryptocurrencyUseCase.getByIdAndCountryId(cryptocurrencyId, 1))
                .thenReturn(cryptocurrency);

        Mockito.when(customerUseCase.updateCryptocurrency(customer, cryptocurrency)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/"+customerId+"/cryptocurrency/"+cryptocurrencyId)
                        .content(objectMapper.writeValueAsString(CustomerVO.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    public Customer customer(){
        return Customer.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .cryptocurrencies(Set.of(this.cryptocurrency()))
                .country(Country.builder()
                        .id(1)
                        .name("name")
                        .build())
                .build();
    }

    public Cryptocurrency cryptocurrency(){
        return Cryptocurrency.builder()
                .id(1)
                .name("name")
                .symbol("symbol")
                .exchangeRate(10.0)
                .build();
    }
}