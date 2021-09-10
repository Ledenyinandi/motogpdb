package nandor.ledenyi.motogpdb.controller;

import nandor.ledenyi.motogpdb.model.dto.CountryDto;
import nandor.ledenyi.motogpdb.model.dto.GrandPrixDto;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.service.CountryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @ApiOperation(value = "List all countries")
    @GetMapping
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }

    @ApiOperation(value = "Get one country by its id")
    @GetMapping("/{id}")
    public CountryDto findById(@PathVariable("id") Long id) {
        return countryService.findById(id);
    }

    @ApiOperation(value = "Add a new country")
    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody CountryDto countryDto) {
        countryService.save(countryDto);
        return ResponseEntity.ok("Country added");
    }

    @ApiOperation(value = "Delete one country by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
        return ResponseEntity.ok("Country deleted");
    }

    @ApiOperation(value = "Update one country by its id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@Valid @RequestBody CountryDto countryDto, @PathVariable("id") Long id) {
        countryService.updateById(countryDto, id);
        return ResponseEntity.ok("Country updated/added");
    }

    @ApiOperation(value = "Get all grandprixs from one country by its id")
    @GetMapping("/{id}/grandprix")
    public List<GrandPrixDto> getGrandPrixsFromCountry(@PathVariable("id") Long id) {
        return countryService.getGrandPrixsFromCountry(id);
    }

    @ApiOperation(value = "Get all riders from one country by its id")
    @GetMapping("/{id}/riders")
    public List<RiderDto> getRidersFromCountry(@PathVariable("id") Long id) {
        return countryService.getRidersFromCountry(id);
    }
}