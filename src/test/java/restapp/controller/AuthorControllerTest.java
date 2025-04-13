package restapp.controller;

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
import restapp.service.AuthorService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {
    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService mockAuthorService;

    @AfterEach
    void tearDown() {
        Mockito.reset(mockAuthorService);
    }

    @Test
    void indexShouldCallFindAllFromService() {
        authorController.index();

        Mockito.verify(mockAuthorService).findAll();
    }

    @Test
    void showShouldCallFindOneFromService() {
        authorController.show(53);

        Mockito.verify(mockAuthorService).findOne(53);
    }

    @Test
    void createShouldCallSaveFromService_AndShouldSendValidDto() {
        BookIncomingDto bookIncomingDto1 = new BookIncomingDto(1, null, null, null);
        BookIncomingDto bookIncomingDto2 = new BookIncomingDto(2, null, null, null);
        AuthorIncomingDto authorIncomingDto = new AuthorIncomingDto(null, "Some Author 35",
                new HashSet<>(Set.of(bookIncomingDto1, bookIncomingDto2)));

        authorController.create(authorIncomingDto);

        ArgumentCaptor<AuthorIncomingDto> argumentCaptor = ArgumentCaptor.forClass(AuthorIncomingDto.class);
        Mockito.verify(mockAuthorService).save(argumentCaptor.capture());

        AuthorIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals("Some Author 35", sentDto.getName());
        Assertions.assertEquals(2, sentDto.getBooks().size());
        Assertions.assertTrue(sentDto.getBooks().contains(
                new BookIncomingDto(1, null, null, null)));
        Assertions.assertTrue(sentDto.getBooks().contains(
                new BookIncomingDto(2, null, null, null)));
    }

    @Test
    void updateShouldCallUpdateFromService_AndShouldSendValidDto() {
        BookIncomingDto bookIncomingDto1 = new BookIncomingDto(3, null, null, null);
        AuthorIncomingDto authorIncomingDto = new AuthorIncomingDto(null, "Some Author 35",
                new HashSet<>(Set.of(bookIncomingDto1)));

        authorController.update(authorIncomingDto, 40);

        ArgumentCaptor<AuthorIncomingDto> argumentCaptor = ArgumentCaptor.forClass(AuthorIncomingDto.class);
        Mockito.verify(mockAuthorService).update(argumentCaptor.capture());

        AuthorIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals(40, sentDto.getId());
        Assertions.assertEquals("Some Author 35", sentDto.getName());
        Assertions.assertEquals(1, sentDto.getBooks().size());
        Assertions.assertTrue(sentDto.getBooks().contains(
                new BookIncomingDto(3, null, null, null)));
    }

    @Test
    void deleteShouldCallDeleteFromService_AndShouldSendId() {
        authorController.delete(45);

        Mockito.verify(mockAuthorService).delete(45);
    }
}
