package restapp.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.AuthorOutgoingDto;
import restapp.model.Author;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BookMapper.class)
public interface AuthorMapper {
    //@Mapping(target = "books", source = "books", ignore = true)
    Author mapToAuthor(AuthorIncomingDto authorIncomingDto, @Context CycleAvoidingMappingContext context);

    //@Mapping(target = "books", source = "books", ignore = true)
    AuthorOutgoingDto mapToAuthorOutgoingDto(Author author, @Context CycleAvoidingMappingContext context);

    //@Mapping(target = "books", source = "books", ignore = true)
    List<AuthorOutgoingDto> mapToAuthorOutgoingDtoList(List<Author> authors, @Context CycleAvoidingMappingContext context);
}
