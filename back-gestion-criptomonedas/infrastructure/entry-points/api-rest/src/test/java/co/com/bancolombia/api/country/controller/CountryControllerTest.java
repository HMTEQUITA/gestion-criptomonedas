package co.com.bancolombia.api.country.controller;

import co.com.bancolombia.api.country.dto.CountryVO;
import co.com.bancolombia.api.country.view.CountryViewDTO;
import co.com.bancolombia.api.exception.HandlerException;
import co.com.bancolombia.countrry.usecase.CountryUseCase;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.view.CountryView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
@ContextConfiguration(classes = {CountryController.class, CountryControllerTest.class, HandlerException.class})
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CountryUseCase countryUseCase;

    @Test
    void getCountriesTest() throws Exception {
        Mockito.when(countryUseCase.getCountries()).thenReturn(List.of(CountryView.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/countries")
                        .content(objectMapper.writeValueAsString(List.of(CountryViewDTO.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCountryByNameTest() throws Exception{
        String param = "col";
        Mockito.when(countryUseCase.getCountryByName(param)).thenReturn(Country.builder().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/"+param)
                        .content(objectMapper.writeValueAsString(CountryVO.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}