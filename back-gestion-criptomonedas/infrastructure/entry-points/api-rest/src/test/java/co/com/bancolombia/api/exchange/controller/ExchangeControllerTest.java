package co.com.bancolombia.api.exchange.controller;

import co.com.bancolombia.api.country.controller.CountryController;
import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.api.exception.HandlerException;
import co.com.bancolombia.api.exchange.dto.ExchangeVO;
import co.com.bancolombia.customer.usecase.CustomerUseCase;
import co.com.bancolombia.customer.view.CustomerView;
import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.exchange.usecase.ExchangeUseCase;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountryController.class)
@ContextConfiguration(classes = {ExchangeController.class, ExchangeControllerTest.class, HandlerException.class})
class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExchangeUseCase exchangeUseCase;

    @Test
    void getExchangesTest() throws Exception {
        Mockito.when(exchangeUseCase.getExchanges())
                .thenReturn(List.of(Exchange.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/exchanges")
                        .content(objectMapper.writeValueAsString(List.of(ExchangeVO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getExchangesByCountryTest() throws Exception {
        Integer id = 1;
        Mockito.when(exchangeUseCase.getExchangeByCountry(id))
                .thenReturn(List.of(Exchange.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/exchanges/country/"+id)
                        .content(objectMapper.writeValueAsString(List.of(ExchangeVO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}