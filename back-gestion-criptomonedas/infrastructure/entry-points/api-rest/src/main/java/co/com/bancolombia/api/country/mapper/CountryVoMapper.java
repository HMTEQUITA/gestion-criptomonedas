package co.com.bancolombia.api.country.mapper;

import co.com.bancolombia.api.country.dto.CountryVO;
import co.com.bancolombia.api.cryptocurrency.mapper.CryptoVoMapper;
import co.com.bancolombia.api.country.view.CountryViewDTO;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.view.CountryView;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
public final class CountryVoMapper {

    public static Country toDto(CountryVO countryVo){
        return Country.builder()
                .id(countryVo.getId())
                .name(countryVo.getName())
                .cryptocurrencies(countryVo.getCryptocurrencies()
                        .stream()
                        .map(CryptoVoMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CountryVO toVo(Country country){
        return CountryVO.builder()
                .id(country.getId())
                .name(country.getName())
                .cryptocurrencies(country.getCryptocurrencies()
                        .stream()
                        .map(CryptoVoMapper::toVO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CountryViewDTO toViewDTO(CountryView countryView){
        return CountryViewDTO.builder()
                .id(countryView.getId())
                .name(countryView.getName())
                .build();
    }
}