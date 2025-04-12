package restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapp.controller.dto.PublisherIncomingDto;
import restapp.controller.dto.PublisherOutgoingDto;
import restapp.service.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublisherOutgoingDto>> index() {
        List<PublisherOutgoingDto> result = publisherService.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherOutgoingDto> show(@PathVariable("id") int id) {
        PublisherOutgoingDto result = publisherService.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherIncomingDto> create(@RequestBody PublisherIncomingDto publisherIncomingDto) {
        publisherService.save(publisherIncomingDto);

        return new ResponseEntity<>(publisherIncomingDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherIncomingDto> update(@RequestBody PublisherIncomingDto publisherIncomingDto,
                                                       @PathVariable("id") int id) {
        publisherIncomingDto.setId(id);
        publisherService.update(publisherIncomingDto);

        return new ResponseEntity<>(publisherIncomingDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        publisherService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
