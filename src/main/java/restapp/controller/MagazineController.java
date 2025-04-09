package restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MagazineService magazineService;

    @Autowired
    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index() throws JsonProcessingException {
        List<MagazineOutgoingDto> list = magazineService.findAll();

        String result = objectMapper.writeValueAsString(list);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> show(@PathVariable("id") int id) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(magazineService.findOne(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody MagazineIncomingDto magazineIncomingDto)
            throws JsonProcessingException {
        magazineService.save(magazineIncomingDto);

        String result = objectMapper.writeValueAsString(magazineIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody MagazineIncomingDto magazineIncomingDto,
                                         @PathVariable("id") int id) throws JsonProcessingException {
        magazineIncomingDto.setId(id);
        magazineService.update(magazineIncomingDto);

        String result = objectMapper.writeValueAsString(magazineIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") int id) throws JsonProcessingException {
        magazineService.delete(id);

        String result = objectMapper.writeValueAsString(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
