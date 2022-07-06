package co.com.bancolombia.api.cryptocurrency.controller;

import co.com.bancolombia.api.country.controller.CountryController;
import co.com.bancolombia.api.country.view.CountryViewDTO;
import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.api.exception.HandlerException;
import co.com.bancolombia.country.view.CountryView;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountryController.class)
@ContextConfiguration(classes = {CryptoController.class, CryptoControllerTest.class, HandlerException.class})
class CryptoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CryptocurrencyUseCase cryptocurrencyUseCase;

    @Test
    void getCryptocurrencies() throws Exception {
        Mockito.when(cryptocurrencyUseCase.getCryptocurrencies())
                .thenReturn(List.of(Cryptocurrency.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/cryptocurrencies")
                        .content(objectMapper.writeValueAsString(List.of(CryptocurrencyVO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCryptocurrenciesByCountry() throws Exception {
        Integer param = 1;
        Mockito.when(cryptocurrencyUseCase.getCryptocurrenciesByCountry(param))
                .thenReturn(List.of(Cryptocurrency.builder().build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/cryptocurrencies/country/"+param)
                        .content(objectMapper.writeValueAsString(List.of(CryptocurrencyVO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCryptocurrenciesByCostumer() throws Exception {
        Long param = 1L;
        Mockito.when(cryptocurrencyUseCase.getCryptocurrenciesByCustomer(param))
                .thenReturn(List.of(Cryptocurrency.builder().build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/cryptocurrencies/customer/"+param)
                        .content(objectMapper.writeValueAsString(List.of(CryptocurrencyVO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}