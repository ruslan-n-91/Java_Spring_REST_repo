package restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.AuthorOutgoingDto;
import restapp.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index() throws JsonProcessingException {
        List<AuthorOutgoingDto> list = authorService.findAll();

        String result = objectMapper.writeValueAsString(list);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> show(@PathVariable("id") int id) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(authorService.findOne(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody AuthorIncomingDto authorIncomingDto) throws JsonProcessingException {
        authorService.save(authorIncomingDto);

        String result = objectMapper.writeValueAsString(authorIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody AuthorIncomingDto authorIncomingDto,
                                         @PathVariable("id") int id) throws JsonProcessingException {
        authorService.update(id, authorIncomingDto);

        String result = objectMapper.writeValueAsString(authorIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") int id) throws JsonProcessingException {
        authorService.delete(id);

        String result = objectMapper.writeValueAsString(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
