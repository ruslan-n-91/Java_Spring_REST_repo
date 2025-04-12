package restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapp.controller.dto.BookIncomingDto;
import restapp.controller.dto.BookOutgoingDto;
import restapp.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookOutgoingDto>> index() {
        List<BookOutgoingDto> result = bookService.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookOutgoingDto> show(@PathVariable("id") int id) {
        BookOutgoingDto result = bookService.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookIncomingDto> create(@RequestBody BookIncomingDto bookIncomingDto) {
        bookService.save(bookIncomingDto);

        return new ResponseEntity<>(bookIncomingDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookIncomingDto> update(@RequestBody BookIncomingDto bookIncomingDto,
                                                  @PathVariable("id") int id) {
        bookIncomingDto.setId(id);
        bookService.update(bookIncomingDto);

        return new ResponseEntity<>(bookIncomingDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        bookService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
