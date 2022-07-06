package co.com.bancolombia.api.country.controller;

import co.com.bancolombia.api.country.dto.CountryVO;
import co.com.bancolombia.api.country.mapper.CountryVoMapper;
import co.com.bancolombia.api.country.view.CountryViewDTO;
import co.com.bancolombia.countrry.usecase.CountryUseCase;
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

    @GetMapping()
    public ResponseEntity<List<CountryViewDTO>> getCountries() {
        var countries = countryUseCase.getCountries()
                .stream()
                .map(CountryVoMapper::toViewDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(countries);
    }

    @GetMapping("/{countryName}")
    public ResponseEntity<CountryVO> getCountryByName(@PathVariable(value = "countryName") String countryName) {
        var country = countryUseCase.getCountryByName(countryName);
        return ResponseEntity.ok().body(CountryVoMapper.toVo(country));
    }

}
