package nandor.ledenyi.motogpdb.controller;

import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.service.RiderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @ApiOperation(value = "List all riders")
    @GetMapping
    public List<RiderDto> findAll() {
        return riderService.findAll();
    }

    @ApiOperation(value = "Get one rider by its id")
    @GetMapping("/{id}")
    public RiderDto findById(@PathVariable("id") Long id) {
        return riderService.findById(id);
    }

    @ApiOperation(value = "Add a new rider")
    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody RiderDto riderDto) {
        riderService.save(riderDto);
        return ResponseEntity.ok("Rider added");
    }

    @ApiOperation(value = "Delete one rider by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        riderService.deleteById(id);
        return ResponseEntity.ok("Rider deleted");
    }

    @ApiOperation(value = "Update one rider by its id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@Valid @RequestBody RiderDto riderDto, @PathVariable("id") Long id) {
        riderService.updateById(riderDto, id);
        return ResponseEntity.ok("Rider updated/added");
    }
}
