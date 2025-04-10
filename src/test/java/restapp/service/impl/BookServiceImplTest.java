package restapp.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import restapp.controller.dto.BookIncomingDto;
import restapp.controller.dto.BookOutgoingDto;
import restapp.controller.mapper.BookMapper;
import restapp.controller.mapper.BookMapperImpl;
import restapp.controller.mapper.CycleAvoidingMappingContext;
import restapp.model.Book;
import restapp.repository.BookRepository;

import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository mockBookRepository;

    @Spy
    BookMapper bookMapper = new BookMapperImpl();

    @Spy
    CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

    @AfterEach
    void tearDown() {
        Mockito.reset(mockBookRepository);
    }

    @Test
    void findAllShouldCallFindAllFromRepository() {
        bookService.findAll();

        Mockito.verify(mockBookRepository).findAll();
    }

    @Test
    void findOneShouldCallFindByIdFromRepository_AndShouldReturnValidDto() {
        Book book = new Book(15, "Some Book 15", 20, new HashSet<>());
        Optional<Book> optionalBook = Optional.of(book);

        Mockito.doReturn(optionalBook).when(mockBookRepository).findById(15);

        BookOutgoingDto dto = bookService.findOne(15);

        Mockito.verify(mockBookRepository).findById(15);
        Assertions.assertEquals(book.getId(), dto.getId());
        Assertions.assertEquals(book.getTitle(), dto.getTitle());
        Assertions.assertEquals(book.getQuantity(), dto.getQuantity());
        Assertions.assertEquals(book.getAuthors().size(), dto.getAuthors().size());
    }

    @Test
    void saveShouldCallSaveFromRepository() {
        BookIncomingDto dto = new BookIncomingDto(null, "Some Book 11", 14, new HashSet<>());
        Book book = new Book(null, "Some Book 11", 14, new HashSet<>());

        bookService.save(dto);

        Mockito.verify(mockBookRepository).save(book);
    }

    @Test
    void updateShouldCallSaveFromRepository() {
        BookIncomingDto dto = new BookIncomingDto(54, "Some Book 11", 14, new HashSet<>());
        Book book = new Book(54, "Some Book 11", 14, new HashSet<>());

        bookService.update(dto);

        Mockito.verify(mockBookRepository).save(book);
    }

    @Test
    void deleteShouldCallDeleteByIdFromRepository() {
        bookService.delete(3);

        Mockito.verify(mockBookRepository).deleteById(3);
    }
}
