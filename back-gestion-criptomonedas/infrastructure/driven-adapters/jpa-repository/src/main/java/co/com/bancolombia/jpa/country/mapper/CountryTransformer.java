package co.com.bancolombia.jpa.country.mapper;

import co.com.bancolombia.country.view.CountryView;
import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.jpa.cryptocurrency.mapper.CryptoTransformer;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
public final class CountryTransformer {

    public static Country ToDto(CountryEntity countryEntity){
        return Country.builder()
                .id(countryEntity.getId())
                .name(countryEntity.getName())
                .cryptocurrencies(countryEntity.getCryptocurrencies()
                        .stream()
                        .map(CryptoTransformer::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CountryEntity toEntity(Country country){
        CountryEntity CountryEntity = new CountryEntity();
        CountryEntity.setId(country.getId());
        CountryEntity.setName(country.getName());
        CountryEntity.setCryptocurrencies(country.getCryptocurrencies().stream()
                .map(CryptoTransformer::toEntity)
                .collect(Collectors.toSet())
        );
        return CountryEntity;
    }

    public static CountryView toView(CountryEntity countryEntity){
        return CountryView.builder()
                .id(countryEntity.getId())
                .name(countryEntity.getName())
                .build();
    }
}