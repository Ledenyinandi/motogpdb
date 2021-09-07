package nandor.ledenyi.motogpdb.controller;

import nandor.ledenyi.motogpdb.model.dto.GrandPrixDto;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.service.GrandPrixService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grandprix")
public class GrandPrixController {

    @Autowired
    private GrandPrixService grandPrixService;

    @ApiOperation(value = "List all grandprixs")
    @GetMapping
    public List<GrandPrixDto> findAll() {
        return grandPrixService.findAll();
    }

    @ApiOperation(value = "Get one grandprix by its id")
    @GetMapping("/{id}")
    public GrandPrixDto findById(@PathVariable("id") Long id) {
        return grandPrixService.findById(id);
    }

    @ApiOperation(value = "Add new grandprix")
    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody GrandPrixDto grandPrixDto) {
        grandPrixService.save(grandPrixDto);
        return ResponseEntity.ok("Grand prix added");
    }

    @ApiOperation(value = "Delete one grandprix by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        grandPrixService.deleteById(id);
        return ResponseEntity.ok("Grand prix deleted");
    }

    @ApiOperation(value = "Update one grandprix by its id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@Valid @RequestBody GrandPrixDto grandPrixDto, @PathVariable("id") Long id) {
        grandPrixService.updateById(grandPrixDto, id);
        return ResponseEntity.ok("Grand prix updated/added");
    }

    @ApiOperation(value = "Get the winning rider of a grand prix by the id of the grand prix")
    @GetMapping("/{id}/winningrider")
    public RiderDto getWinningRiderByGrandPrixId(@PathVariable("id") Long id) {
        return grandPrixService.getWinningRiderByGrandPrixId(id);
    }
}
