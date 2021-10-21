package nandor.ledenyi.motogpdb.controller;

import nandor.ledenyi.motogpdb.exception.DataNotFoundException;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.service.RiderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RiderController.class)
public class RiderControllerUnitTest {

    private static RiderDto riderDto1;
    private static RiderDto riderDto2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RiderService riderService;

    @BeforeEach
    public void setup() {
        riderDto1 = RiderDto.builder()
                .id(1L)
                .name("Stefan Bradl")
                .bikeNumber(14)
                .countryId(6L)
                .build();
        riderDto2 = RiderDto.builder()
                .id(2L)
                .name("Hector Faubel")
                .bikeNumber(73)
                .countryId(1L)
                .build();
    }

    @Test
    public void testFindALl_shouldReturnListOfRiders() throws Exception {
        when(riderService.findAll()).thenReturn(List.of(riderDto1, riderDto2));
        mockMvc.perform(get("/rider"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void testFindById_shouldReturnRiderDto1() throws Exception {
        when(riderService.findById(1L)).thenReturn(riderDto1);
        mockMvc.perform(get("/rider/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Stefan Bradl")))
                .andExpect(jsonPath("$.bikeNumber", is(14)))
                .andExpect(jsonPath("$.countryId", is(6)));
    }

    @Test
    public void testFindById_shouldReturnNotFoundStatus() throws Exception {
        when(riderService.findById(3L)).thenThrow(new DataNotFoundException());
        mockMvc.perform(get("/rider/3"))
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof DataNotFoundException));
    }
}
