package nandor.ledenyi.motogpdb.controller;

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
public class GrandPrixControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testFindAll_shouldReturnListOfGrandPrixs() {
        ResponseEntity<GrandPrixDto[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/grandprix", GrandPrixDto[].class);
        List<GrandPrixDto> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(8, actual.size());
        Assertions.assertEquals("Portimao Grand Prix", actual.get(1).getName());
    }

    @Test
    public void testFindById_shouldReturnGrandPrixByTheGivenId() {
        GrandPrixDto grandPrixDto = restTemplate.getForObject(BASE_URL + port + "/grandprix/3", GrandPrixDto.class);
        Assertions.assertEquals("Jerez Grand Prix", grandPrixDto.getName());
    }

    @Test
    public void testSave_shouldAddOneGrandPrix() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GrandPrixDto> httpEntity = new HttpEntity<>(new GrandPrixDto(0L, "Hungary Grand Prix", 1L, 2L), headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL + port + "/grandprix", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        GrandPrixDto grandPrixDto = restTemplate.getForObject(BASE_URL + port + "/grandprix/9", GrandPrixDto.class);
        Assertions.assertEquals("Hungary Grand Prix", grandPrixDto.getName());
    }

    @Test
    public void testUpdate_shouldUpdateGrandPrixByTheGivenId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GrandPrixDto> httpEntity = new HttpEntity<>(new GrandPrixDto(2L, "Slovakia Grand Prix", 2L, 8L), headers);
        restTemplate.put(BASE_URL + port + "/grandprix/2", httpEntity, String.class);
        GrandPrixDto grandPrixDto = restTemplate.getForObject(BASE_URL + port + "/grandprix/2", GrandPrixDto.class);
        Assertions.assertEquals("Slovakia Grand Prix", grandPrixDto.getName());
        Assertions.assertEquals(8, grandPrixDto.getWinningRiderId());
    }

    @Test
    public void testDelete_shouldDeleteGrandPrixByTheGivenId() {
        restTemplate.delete(BASE_URL + port + "/grandprix/1");
        ResponseEntity<GrandPrixDto[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/grandprix", GrandPrixDto[].class);
        List<GrandPrixDto> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(7, actual.size());
    }

    @Test
    public void testGetWinningRiderByGrandPrixId_shouldReturnOneRider() {
        ResponseEntity<RiderDto> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/grandprix/2/winningrider", RiderDto.class);
        Assertions.assertEquals("Fabio Quartararo", responseEntity.getBody().getName());

    }
}
