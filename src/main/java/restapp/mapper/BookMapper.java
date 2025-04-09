package restapp.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import restapp.controller.dto.BookIncomingDto;
import restapp.controller.dto.BookOutgoingDto;
import restapp.model.Book;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AuthorMapper.class)
public interface BookMapper {
    //@Mapping(target = "authors", source = "authors", ignore = true)
    Book mapToBook(BookIncomingDto bookIncomingDto, @Context CycleAvoidingMappingContext context);

    //@Mapping(target = "authors", source = "authors", ignore = true)
    BookOutgoingDto mapToBookOutgoingDto(Book book, @Context CycleAvoidingMappingContext context);

    //@Mapping(target = "authors", source = "authors", ignore = true)
    List<BookOutgoingDto> mapToBookOutgoingDtoList(List<Book> books, @Context CycleAvoidingMappingContext context);
}
