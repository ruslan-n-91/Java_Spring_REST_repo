package restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index() throws JsonProcessingException {
//        Author author = new Author(14, "Author 14", new ArrayList<>());
//        List<Author> list = new ArrayList<>();
//        list.add(author);
//        Book book = new Book(15, "Book 15", 56, list);
//        String result = objectMapper.writeValueAsString(bookMapper.mapToBookOutgoingDto(book));

        List<BookOutgoingDto> list = bookService.findAll();

        String result = objectMapper.writeValueAsString(list);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> show(@PathVariable("id") int id) throws JsonProcessingException {
//        Author author = new Author(id + 1, "Author " + (id + 1), new ArrayList<>());
//        List<Author> list = new ArrayList<>();
//        list.add(author);
//        Book book = new Book(id, "Book " + id, 56, list);
//        String result = objectMapper.writeValueAsString(bookMapper.mapToBookOutgoingDto(book));
//        return new ResponseEntity<>(result, HttpStatus.OK);

        String result = objectMapper.writeValueAsString(bookService.findOne(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody BookIncomingDto bookIncomingDto) throws JsonProcessingException {
//        System.out.println(bookIncomingDto);
//        String result = objectMapper.writeValueAsString(bookIncomingDto);

        bookService.save(bookIncomingDto);

        String result = objectMapper.writeValueAsString(bookIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody BookIncomingDto bookIncomingDto,
                                         @PathVariable("id") int id) throws JsonProcessingException {
//        System.out.println(id);
//        System.out.println(bookIncomingDto);
//        String result = objectMapper.writeValueAsString(bookIncomingDto);

        bookService.update(id, bookIncomingDto);

        String result = objectMapper.writeValueAsString(bookIncomingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") int id) throws JsonProcessingException {
//        System.out.println(id);
//        String result = objectMapper.writeValueAsString(id);

        bookService.delete(id);

        String result = objectMapper.writeValueAsString(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
