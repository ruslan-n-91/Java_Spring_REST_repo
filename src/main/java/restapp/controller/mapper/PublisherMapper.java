package restapp.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import restapp.controller.dto.PublisherIncomingDto;
import restapp.controller.dto.PublisherOutgoingDto;
import restapp.model.Publisher;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = MagazineMapper.class)
public interface PublisherMapper {
    Publisher mapToPublisher(PublisherIncomingDto publisherIncomingDto,
                             @Context CycleAvoidingMappingContext context);

    PublisherOutgoingDto mapToPublisherOutgoingDto(Publisher publisher,
                                                   @Context CycleAvoidingMappingContext context);

    List<PublisherOutgoingDto> mapToPublisherOutgoingDtoList(List<Publisher> publishers,
                                                             @Context CycleAvoidingMappingContext context);
}
