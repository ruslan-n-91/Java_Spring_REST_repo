package restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index() throws JsonProcessingException {
        List<PublisherOutgoingDto> list = publisherService.findAll();

        String result = objectMapper.writeValueAsString(list);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> show(@PathVariable("id") int id) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(publisherService.findOne(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody PublisherIncomingDto publisherIncomingDto)
            throws JsonProcessingException {
        publisherService.save(publisherIncomingDto);

        String result = objectMapper.writeValueAsString(publisherIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody PublisherIncomingDto publisherIncomingDto,
                                         @PathVariable("id") int id) throws JsonProcessingException {
        publisherIncomingDto.setId(id);
        publisherService.update(publisherIncomingDto);

        String result = objectMapper.writeValueAsString(publisherIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") int id) throws JsonProcessingException {
        publisherService.delete(id);

        String result = objectMapper.writeValueAsString(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
