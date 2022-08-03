package co.com.bancolombia.api.cryptocurrency.controller;

import co.com.bancolombia.api.country.dto.CountryVO;
import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.api.cryptocurrency.mapper.CryptoVoMapper;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Search cryptocurrencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of cryptocurrencies",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CryptocurrencyVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any cryptocurrency",
                    content = @Content) })
    @GetMapping()
    public ResponseEntity<List<CryptocurrencyVO>> getCryptocurrencies() {
        var crypto = cryptocurrencyUseCase.getCryptocurrencies()
                .stream()
                .map(CryptoVoMapper::toVO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(crypto);
    }

    @Operation(summary = "Search cryptocurrencies by country id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of cryptocurrencies",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CryptocurrencyVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any cryptocurrency",
                    content = @Content) })
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CryptocurrencyVO>> getCryptocurrenciesByCountry(@PathVariable(value = "countryId") Integer countryId) {
        var crypto =  cryptocurrencyUseCase.getCryptocurrenciesByCountry(countryId)
                .stream()
                .map(CryptoVoMapper::toVO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(crypto);
    }

    @Operation(summary = "Search cryptocurrencies by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of cryptocurrencies",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CryptocurrencyVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any cryptocurrency",
                    content = @Content) })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CryptocurrencyVO>> getCryptocurrenciesByCostumer(@PathVariable(value = "customerId") Long customerId) {
        var crypto = cryptocurrencyUseCase.getCryptocurrenciesByCustomer(customerId)
                .stream()
                .map(CryptoVoMapper::toVO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(crypto);
    }
}