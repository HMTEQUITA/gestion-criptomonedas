package co.com.bancolombia.api.cryptocurrency.controller;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.api.cryptocurrency.mapper.CryptoVoMapper;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cryptocurrencies")
@AllArgsConstructor
public class CryptoController {
    private final CryptocurrencyUseCase cryptocurrencyUseCase;

    @GetMapping()
    public ResponseEntity<List<CryptocurrencyVO>> getCryptocurrencies() {
        var crypto = cryptocurrencyUseCase.getCryptocurrencies()
                .stream()
                .map(CryptoVoMapper::toVO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(crypto);
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CryptocurrencyVO>> getCryptocurrenciesByCountry(@PathVariable(value = "countryId") Integer countryId) {
        var crypto =  cryptocurrencyUseCase.getCryptocurrenciesByCountry(countryId)
                .stream()
                .map(CryptoVoMapper::toVO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(crypto);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CryptocurrencyVO>> getCryptocurrenciesByCostumer(@PathVariable(value = "customerId") Long customerId) {
        var crypto = cryptocurrencyUseCase.getCryptocurrenciesByCustomer(customerId)
                .stream()
                .map(CryptoVoMapper::toVO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(crypto);
    }
}