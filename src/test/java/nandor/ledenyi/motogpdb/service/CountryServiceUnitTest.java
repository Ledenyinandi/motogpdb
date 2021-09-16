package nandor.ledenyi.motogpdb.service;

import nandor.ledenyi.motogpdb.mapper.CountryMapper;
import nandor.ledenyi.motogpdb.model.Country;
import nandor.ledenyi.motogpdb.model.GrandPrix;
import nandor.ledenyi.motogpdb.model.Rider;
import nandor.ledenyi.motogpdb.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceUnitTest {

    private CountryRepository countryRepository;

    private CountryService countryService;

    private CountryMapper countryMapper;

    @BeforeEach
    void initUseCase() {
        countryRepository = mock(CountryRepository.class);
        countryMapper = new CountryMapper();
        countryService = new CountryService(countryRepository, countryMapper);
    }

    @Test
    public void testSave_shouldReturnNewCountry() {
        Country country = new Country();
        country.setId(1L);
        country.setName("Hungary");
        country.setGrandPrixs(List.of(new GrandPrix()));
        country.setRiders(List.of(new Rider()));

        when(countryRepository.save(any(Country.class))).thenReturn(country);

        Country savedCountry = countryRepository.save(country);
        Assertions.assertFalse(savedCountry.getName().isEmpty());
    }

    @Test
    public void testFindAll_shouldReturnListOfCountries() {
        Country country = new Country();
        country.setId(1L);
        country.setName("Hungary");
        country.setGrandPrixs(List.of(new GrandPrix()));
        country.setRiders(List.of(new Rider()));
        List<Country> countries = new ArrayList<>();
        countries.add(country);

        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> fetchedCountries = new ArrayList<>();
        countryService.findAll().forEach(countryDto -> fetchedCountries.add(countryMapper.convertToEntity(countryDto)));
        Assertions.assertTrue(fetchedCountries.size() > 0);
    }

    @Test
    public void testFindById_shouldReturnCountryWithGivenId () {
        Country country = new Country();
        country.setId(1L);
        country.setName("Hungary");
        country.setGrandPrixs(List.of(new GrandPrix()));
        country.setRiders(List.of(new Rider()));

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        Country foundCountry = countryMapper.convertToEntity(countryService.findById(1L));
        Assertions.assertFalse(foundCountry.getName().isEmpty());
    }
}