package restapp.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import restapp.controller.dto.MagazineIncomingDto;
import restapp.controller.dto.MagazineOutgoingDto;
import restapp.model.Magazine;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PublisherMapper.class)
public interface MagazineMapper {
    Magazine mapToMagazine(MagazineIncomingDto magazineIncomingDto,
                           @Context CycleAvoidingMappingContext context);

    MagazineOutgoingDto mapToMagazineOutgoingDto(Magazine magazine,
                                                 @Context CycleAvoidingMappingContext context);

    List<MagazineOutgoingDto> mapToMagazineOutgoingDtoList(List<Magazine> magazines,
                                                           @Context CycleAvoidingMappingContext context);
}
