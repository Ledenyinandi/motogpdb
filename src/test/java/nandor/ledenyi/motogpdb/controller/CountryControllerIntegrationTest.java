package nandor.ledenyi.motogpdb.controller;

import nandor.ledenyi.motogpdb.model.dto.CountryDto;
import nandor.ledenyi.motogpdb.model.dto.GrandPrixDto;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CountryControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testFindAll_shouldReturnListOfCountries() {
        ResponseEntity<CountryDto[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/country", CountryDto[].class);
        List<CountryDto> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(8, actual.size());
        Assertions.assertEquals("Spain", actual.get(0).getName());
    }

    @Test
    public void testFindById_shouldReturnCountryByTheGivenId() {
        CountryDto countryDto = restTemplate.getForObject(BASE_URL + port + "/country/2", CountryDto.class);
        Assertions.assertEquals("Italy", countryDto.getName());
    }

    @Test
    public void testSave_shouldAddOneCountry() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CountryDto> httpEntity = new HttpEntity<>(new CountryDto(0L, "Hungary"), headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL + port + "/country", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        CountryDto countryDto = restTemplate.getForObject(BASE_URL + port + "/country/9", CountryDto.class);
        Assertions.assertEquals("Hungary", countryDto.getName());
    }

    @Test
    public void testUpdate_shouldUpdateCountryByTheGivenId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CountryDto> httpEntity = new HttpEntity<>(new CountryDto(1L, "Hungary"), headers);
        restTemplate.put(BASE_URL + port + "/country/1", httpEntity, String.class);
        CountryDto countryDto = restTemplate.getForObject(BASE_URL + port + "/country/1", CountryDto.class);
        Assertions.assertEquals("Hungary", countryDto.getName());
    }

    @Test
    public void testGetGrandPrixsFromCountry_shouldReturnListOfGrandPrixs() {
        ResponseEntity<GrandPrixDto[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/country/1/grandprix", GrandPrixDto[].class);
        List<GrandPrixDto> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("Catalan Grand Prix", actual.get(1).getName());
    }

    @Test
    public void testGetRidersFromCountry_shouldReturnListOfRiders() {
        ResponseEntity<RiderDto[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/country/1/riders", RiderDto[].class);
        List<RiderDto> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("Maverick Vinales", actual.get(2).getName());
    }
}
