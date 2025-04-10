package restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.BookIncomingDto;
import restapp.service.BookService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService mockBookService;

    @AfterEach
    void tearDown() {
        Mockito.reset(mockBookService);
    }

    @Test
    void indexShouldCallFindAllFromService() throws JsonProcessingException {
        bookController.index();

        Mockito.verify(mockBookService).findAll();
    }

    @Test
    void showShouldCallFindOneFromService() throws JsonProcessingException {
        bookController.show(53);

        Mockito.verify(mockBookService).findOne(53);
    }

    @Test
    void createShouldCallSaveFromService_AndShouldSendValidDto() throws JsonProcessingException {
        AuthorIncomingDto authorIncomingDto1 = new AuthorIncomingDto(1, null, null);
        AuthorIncomingDto authorIncomingDto2 = new AuthorIncomingDto(2, null, null);
        BookIncomingDto bookIncomingDto = new BookIncomingDto(null, "Some Book 35", 20,
                new HashSet<>(Set.of(authorIncomingDto1, authorIncomingDto2)));

        bookController.create(bookIncomingDto);

        ArgumentCaptor<BookIncomingDto> argumentCaptor = ArgumentCaptor.forClass(BookIncomingDto.class);
        Mockito.verify(mockBookService).save(argumentCaptor.capture());

        BookIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals("Some Book 35", sentDto.getTitle());
        Assertions.assertEquals(20, sentDto.getQuantity());
        Assertions.assertEquals(2, sentDto.getAuthors().size());
        Assertions.assertTrue(sentDto.getAuthors().contains(
                new AuthorIncomingDto(1, null, null)));
        Assertions.assertTrue(sentDto.getAuthors().contains(
                new AuthorIncomingDto(2, null, null)));
    }

    @Test
    void updateShouldCallUpdateFromService_AndShouldSendValidDto() throws JsonProcessingException {
        AuthorIncomingDto authorIncomingDto1 = new AuthorIncomingDto(3, null, null);
        BookIncomingDto bookIncomingDto = new BookIncomingDto(null, "Some Book 35", 20,
                new HashSet<>(Set.of(authorIncomingDto1)));

        bookController.update(bookIncomingDto, 40);

        ArgumentCaptor<BookIncomingDto> argumentCaptor = ArgumentCaptor.forClass(BookIncomingDto.class);
        Mockito.verify(mockBookService).update(argumentCaptor.capture());

        BookIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals(40, sentDto.getId());
        Assertions.assertEquals("Some Book 35", sentDto.getTitle());
        Assertions.assertEquals(20, sentDto.getQuantity());
        Assertions.assertEquals(1, sentDto.getAuthors().size());
        Assertions.assertTrue(sentDto.getAuthors().contains(
                new AuthorIncomingDto(3, null, null)));
    }

    @Test
    void deleteShouldCallDeleteFromService_AndShouldSendId() throws JsonProcessingException {
        bookController.delete(45);

        Mockito.verify(mockBookService).delete(45);
    }
}
