package co.com.bancolombia.api.exchange.controller;

import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.api.exchange.dto.ExchangeVO;
import co.com.bancolombia.api.exchange.mapper.ExchangeVoMapper;
import co.com.bancolombia.exchange.usecase.ExchangeUseCase;
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
@RequestMapping("/exchanges")
@AllArgsConstructor
public class ExchangeController {
    private final ExchangeUseCase exchangeUseCase;

    @Operation(summary = "Search exchanges")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exchanges",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerViewDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any exchange",
                    content = @Content) })
    @GetMapping()
    public ResponseEntity<List<ExchangeVO>> getExchanges() {
        var exchange = exchangeUseCase.getExchanges()
                .stream()
                .map(ExchangeVoMapper::toVo)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(exchange);
    }

    @Operation(summary = "Search exchanges by country id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exchanges",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerViewDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any exchange",
                    content = @Content) })
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<ExchangeVO>> getExchangesByCountry(@PathVariable(value = "countryId") Integer countryId) {
        var exchange = exchangeUseCase.getExchangeByCountry(countryId)
                .stream()
                .map(ExchangeVoMapper::toVo)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(exchange);
    }
}
