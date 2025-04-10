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
import restapp.controller.dto.*;
import restapp.controller.mapper.*;
import restapp.model.Magazine;
import restapp.model.Publisher;
import restapp.repository.MagazineRepository;

import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MagazineServiceImplTest {
    @InjectMocks
    MagazineServiceImpl magazineService;

    @Mock
    MagazineRepository mockMagazineRepository;

    @Spy
    @InjectMocks
    MagazineMapper magazineMapper = new MagazineMapperImpl();

    @Spy
    PublisherMapper publisherMapper = new PublisherMapperImpl();

    @Spy
    CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

    @AfterEach
    void tearDown() {
        Mockito.reset(mockMagazineRepository);
    }

    @Test
    void findAllShouldCallFindAllFromRepository() {
        magazineService.findAll();

        Mockito.verify(mockMagazineRepository).findAll();
    }

    @Test
    void findOneShouldCallFindByIdFromRepository_AndShouldReturnValidDto() {
        Magazine magazine = new Magazine(15, "Some Magazine 15", 20,
                new Publisher(11, "Some Publisher 11", new HashSet<>()));
        Optional<Magazine> optionalMagazine = Optional.of(magazine);

        Mockito.doReturn(optionalMagazine).when(mockMagazineRepository).findById(15);

        MagazineOutgoingDto dto = magazineService.findOne(15);

        Mockito.verify(mockMagazineRepository).findById(15);
        Assertions.assertEquals(magazine.getId(), dto.getId());
        Assertions.assertEquals(magazine.getTitle(), dto.getTitle());
        Assertions.assertEquals(magazine.getQuantity(), dto.getQuantity());
        Assertions.assertEquals(magazine.getPublisher().getId(), dto.getPublisher().getId());
        Assertions.assertEquals(magazine.getPublisher().getName(), dto.getPublisher().getName());
    }

    @Test
    void saveShouldCallSaveFromRepository() {
        MagazineIncomingDto dto = new MagazineIncomingDto(null, "Some Magazine 11", 14,
                new PublisherIncomingDto(11, "Some Publisher 11", new HashSet<>()));
        Magazine magazine = new Magazine(null, "Some Magazine 11", 14,
                new Publisher(11, "Some Publisher 11", new HashSet<>()));

        magazineService.save(dto);

        Mockito.verify(mockMagazineRepository).save(magazine);
    }

    @Test
    void updateShouldCallSaveFromRepository() {
        MagazineIncomingDto dto = new MagazineIncomingDto(null, "Some Magazine 11", 14,
                new PublisherIncomingDto(11, "Some Publisher 11", new HashSet<>()));
        Magazine magazine = new Magazine(null, "Some Magazine 11", 14,
                new Publisher(11, "Some Publisher 11", new HashSet<>()));

        magazineService.update(dto);

        Mockito.verify(mockMagazineRepository).save(magazine);
    }

    @Test
    void deleteShouldCallDeleteByIdFromRepository() {
        magazineService.delete(3);

        Mockito.verify(mockMagazineRepository).deleteById(3);
    }
}
