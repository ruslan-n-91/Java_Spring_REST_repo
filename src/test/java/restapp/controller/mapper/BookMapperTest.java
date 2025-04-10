package restapp.controller.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import restapp.controller.dto.BookIncomingDto;
import restapp.model.Book;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class BookMapperTest {
    @Spy
    @InjectMocks
    BookMapper bookMapper = new BookMapperImpl();

    @Spy
    AuthorMapper authorMapper = new AuthorMapperImpl();

    @Spy
    CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

    @Test
    void findAllShouldCallFindAllFromRepository() {
        BookIncomingDto dto = new BookIncomingDto(11, "Some Book 11", 14, new HashSet<>());

        Book book = bookMapper.mapToBook(dto, context);

        Mockito.verify(bookMapper).mapToBook(dto, context);

        Assertions.assertEquals(dto.getId(), book.getId());
        Assertions.assertEquals(dto.getTitle(), book.getTitle());
        Assertions.assertEquals(dto.getQuantity(), book.getQuantity());
        Assertions.assertEquals(dto.getAuthors().size(), book.getAuthors().size());
    }
}
