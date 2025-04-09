package restapp.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import restapp.controller.dto.BookIncomingDto;
import restapp.controller.dto.BookOutgoingDto;
import restapp.model.Book;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AuthorMapper.class)
public interface BookMapper {
    Book mapToBook(BookIncomingDto bookIncomingDto,
                   @Context CycleAvoidingMappingContext context);

    BookOutgoingDto mapToBookOutgoingDto(Book book,
                                         @Context CycleAvoidingMappingContext context);

    List<BookOutgoingDto> mapToBookOutgoingDtoList(List<Book> books,
                                                   @Context CycleAvoidingMappingContext context);
}
