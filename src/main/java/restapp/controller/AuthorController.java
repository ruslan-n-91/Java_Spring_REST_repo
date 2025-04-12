package restapp.controller;

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
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorOutgoingDto>> index() {
        List<AuthorOutgoingDto> result = authorService.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorOutgoingDto> show(@PathVariable("id") int id) {
        AuthorOutgoingDto result = authorService.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorIncomingDto> create(@RequestBody AuthorIncomingDto authorIncomingDto) {
        authorService.save(authorIncomingDto);

        return new ResponseEntity<>(authorIncomingDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorIncomingDto> update(@RequestBody AuthorIncomingDto authorIncomingDto,
                                                    @PathVariable("id") int id) {
        authorIncomingDto.setId(id);
        authorService.update(authorIncomingDto);

        return new ResponseEntity<>(authorIncomingDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        authorService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
