package co.com.bancolombia.api.exchange.controller;

import co.com.bancolombia.api.exchange.dto.ExchangeVO;
import co.com.bancolombia.api.exchange.mapper.ExchangeVoMapper;
import co.com.bancolombia.exchange.usecase.ExchangeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exchanges")
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeUseCase exchangeUseCase;

    @GetMapping()
    public ResponseEntity<List<ExchangeVO>> getExchanges() {
        var exchange = exchangeUseCase.getExchanges()
                .stream()
                .map(ExchangeVoMapper::toVo)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(exchange);
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<ExchangeVO>> getExchangesByCountry(@PathVariable(value = "countryId") Integer countryId) {
        var exchange = exchangeUseCase.getExchangeByCountry(countryId)
                .stream()
                .map(ExchangeVoMapper::toVo)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(exchange);
    }
}
