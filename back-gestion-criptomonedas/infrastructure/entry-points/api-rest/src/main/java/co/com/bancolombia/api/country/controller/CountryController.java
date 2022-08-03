package co.com.bancolombia.api.country.controller;

import co.com.bancolombia.api.country.dto.CountryVO;
import co.com.bancolombia.api.country.mapper.CountryVoMapper;
import co.com.bancolombia.api.country.view.CountryViewDTO;
import co.com.bancolombia.countrry.usecase.CountryUseCase;
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
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    private final CountryUseCase countryUseCase;

    @Operation(summary = "Search countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of countries",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryViewDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any country",
                    content = @Content) })
    @GetMapping()
    public ResponseEntity<List<CountryViewDTO>> getCountries() {
        var countries = countryUseCase.getCountries()
                .stream()
                .map(CountryVoMapper::toViewDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(countries);
    }

    @Operation(summary = "Search country by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The country was found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Country not found",
                    content = @Content) })
    @GetMapping("/{countryName}")
    public ResponseEntity<CountryVO> getCountryByName(@PathVariable(value = "countryName") String countryName) {
        var country = countryUseCase.getCountryByName(countryName);
        return ResponseEntity.ok().body(CountryVoMapper.toVo(country));
    }

}
