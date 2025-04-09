package restapp.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.AuthorOutgoingDto;
import restapp.model.Author;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BookMapper.class)
public interface AuthorMapper {
    Author mapToAuthor(AuthorIncomingDto authorIncomingDto,
                       @Context CycleAvoidingMappingContext context);

    AuthorOutgoingDto mapToAuthorOutgoingDto(Author author,
                                             @Context CycleAvoidingMappingContext context);

    List<AuthorOutgoingDto> mapToAuthorOutgoingDtoList(List<Author> authors,
                                                       @Context CycleAvoidingMappingContext context);
}
