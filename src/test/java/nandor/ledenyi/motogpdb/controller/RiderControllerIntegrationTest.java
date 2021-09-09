package nandor.ledenyi.motogpdb.controller;

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
public class RiderControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testFindAll_shouldReturnListOfRiders() {
        ResponseEntity<RiderDto[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/rider", RiderDto[].class);
        List<RiderDto> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(9, actual.size());
        Assertions.assertEquals("Joan Mir", actual.get(3).getName());
    }

    @Test
    public void testFindById_shouldReturnRiderByTheGivenId() {
        RiderDto riderDto = restTemplate.getForObject(BASE_URL + port + "/rider/2", RiderDto.class);
        Assertions.assertEquals("Johann Zarco", riderDto.getName());
    }

    @Test
    public void testSave_shouldAddOneRider() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RiderDto> httpEntity = new HttpEntity<>(new RiderDto(0L, "Max Biaggi", 38, 1L), headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL + port + "/rider", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        RiderDto riderDto = restTemplate.getForObject(BASE_URL + port + "/rider/10", RiderDto.class);
        Assertions.assertEquals("Max Biaggi", riderDto.getName());
    }

    @Test
    public void testUpdate_shouldUpdateRiderByTheGivenId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RiderDto> httpEntity = new HttpEntity<>(new RiderDto(1L, "Max Biaggi", 60, 5L), headers);
        restTemplate.put(BASE_URL + port + "/rider/1", httpEntity, String.class);
        RiderDto riderDto = restTemplate.getForObject(BASE_URL + port + "/rider/1", RiderDto.class);
        Assertions.assertEquals("Max Biaggi", riderDto.getName());
        Assertions.assertEquals(60, riderDto.getBikeNumber());
    }
}
