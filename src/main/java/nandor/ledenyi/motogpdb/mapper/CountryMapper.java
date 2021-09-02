package nandor.ledenyi.motogpdb.mapper;

import nandor.ledenyi.motogpdb.model.Country;
import nandor.ledenyi.motogpdb.model.dto.CountryDto;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryDto convertToDto(Country country) {
        return new CountryDto(
                country.getId(),
                country.getName()
        );
    }

    public Country convertToEntity(CountryDto countryDto) {
        return new Country(
                countryDto.getId(),
                countryDto.getName()
        );
    }
}
