package restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapp.controller.dto.MagazineIncomingDto;
import restapp.controller.dto.MagazineOutgoingDto;
import restapp.service.MagazineService;

import java.util.List;

@RestController
@RequestMapping("/magazines")
public class MagazineController {
    private final MagazineService magazineService;

    @Autowired
    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MagazineOutgoingDto>> index() {
        List<MagazineOutgoingDto> result = magazineService.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagazineOutgoingDto> show(@PathVariable("id") int id) {
        MagazineOutgoingDto result = magazineService.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagazineIncomingDto> create(@RequestBody MagazineIncomingDto magazineIncomingDto) {
        magazineService.save(magazineIncomingDto);

        return new ResponseEntity<>(magazineIncomingDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagazineIncomingDto> update(@RequestBody MagazineIncomingDto magazineIncomingDto,
                                                      @PathVariable("id") int id) {
        magazineIncomingDto.setId(id);
        magazineService.update(magazineIncomingDto);

        return new ResponseEntity<>(magazineIncomingDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        magazineService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
