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
import restapp.controller.dto.PublisherIncomingDto;
import restapp.controller.dto.PublisherOutgoingDto;
import restapp.controller.mapper.*;
import restapp.model.Publisher;
import restapp.repository.PublisherRepository;

import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {
    @InjectMocks
    PublisherServiceImpl publisherService;

    @Mock
    PublisherRepository mockPublisherRepository;

    @Spy
    PublisherMapper publisherMapper = new PublisherMapperImpl();

    @Spy
    CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

    @AfterEach
    void tearDown() {
        Mockito.reset(mockPublisherRepository);
    }

    @Test
    void findAllShouldCallFindAllFromRepository() {
        publisherService.findAll();

        Mockito.verify(mockPublisherRepository).findAll();
    }

    @Test
    void findOneShouldCallFindByIdFromRepository_AndShouldReturnValidDto() {
        Publisher publisher = new Publisher(15, "Some Publisher 15", new HashSet<>());
        Optional<Publisher> optionalPublisher = Optional.of(publisher);

        Mockito.doReturn(optionalPublisher).when(mockPublisherRepository).findById(15);

        PublisherOutgoingDto dto = publisherService.findOne(15);

        Mockito.verify(mockPublisherRepository).findById(15);
        Assertions.assertEquals(publisher.getId(), dto.getId());
        Assertions.assertEquals(publisher.getName(), dto.getName());
        Assertions.assertEquals(publisher.getMagazines().size(), dto.getMagazines().size());
    }

    @Test
    void saveShouldCallSaveFromRepository() {
        PublisherIncomingDto dto = new PublisherIncomingDto(null, "Some Publisher 11", new HashSet<>());
        Publisher publisher = new Publisher(null, "Some Publisher 11", new HashSet<>());

        publisherService.save(dto);

        Mockito.verify(mockPublisherRepository).save(publisher);
    }

    @Test
    void updateShouldCallSaveFromRepository() {
        PublisherIncomingDto dto = new PublisherIncomingDto(null, "Some Publisher 11", new HashSet<>());
        Publisher publisher = new Publisher(null, "Some Publisher 11", new HashSet<>());

        publisherService.update(dto);

        Mockito.verify(mockPublisherRepository).save(publisher);
    }

    @Test
    void deleteShouldCallDeleteByIdFromRepository() {
        publisherService.delete(3);

        Mockito.verify(mockPublisherRepository).deleteById(3);
    }
}
