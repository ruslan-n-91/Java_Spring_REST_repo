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
import restapp.controller.dto.MagazineIncomingDto;
import restapp.controller.dto.PublisherIncomingDto;
import restapp.service.PublisherService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {
    @InjectMocks
    private PublisherController publisherController;

    @Mock
    private PublisherService mockPublisherService;

    @AfterEach
    void tearDown() {
        Mockito.reset(mockPublisherService);
    }

    @Test
    void indexShouldCallFindAllFromService() {
        publisherController.index();

        Mockito.verify(mockPublisherService).findAll();
    }

    @Test
    void showShouldCallFindOneFromService() {
        publisherController.show(53);

        Mockito.verify(mockPublisherService).findOne(53);
    }

    @Test
    void createShouldCallSaveFromService_AndShouldSendValidDto() {
        MagazineIncomingDto magazineIncomingDto1 = new MagazineIncomingDto(1, null, null, null);
        MagazineIncomingDto magazineIncomingDto2 = new MagazineIncomingDto(2, null, null, null);
        PublisherIncomingDto publisherIncomingDto = new PublisherIncomingDto(null, "Some Publisher 35",
                new HashSet<>(Set.of(magazineIncomingDto1, magazineIncomingDto2)));

        publisherController.create(publisherIncomingDto);

        ArgumentCaptor<PublisherIncomingDto> argumentCaptor = ArgumentCaptor.forClass(PublisherIncomingDto.class);
        Mockito.verify(mockPublisherService).save(argumentCaptor.capture());

        PublisherIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals("Some Publisher 35", sentDto.getName());
        Assertions.assertEquals(2, sentDto.getMagazines().size());
        Assertions.assertTrue(sentDto.getMagazines().contains(
                new MagazineIncomingDto(1, null, null, null)));
        Assertions.assertTrue(sentDto.getMagazines().contains(
                new MagazineIncomingDto(2, null, null, null)));
    }

    @Test
    void updateShouldCallUpdateFromService_AndShouldSendValidDto() {
        MagazineIncomingDto magazineIncomingDto1 = new MagazineIncomingDto(3, null, null, null);
        PublisherIncomingDto publisherIncomingDto = new PublisherIncomingDto(null, "Some Publisher 35",
                new HashSet<>(Set.of(magazineIncomingDto1)));

        publisherController.update(publisherIncomingDto, 40);

        ArgumentCaptor<PublisherIncomingDto> argumentCaptor = ArgumentCaptor.forClass(PublisherIncomingDto.class);
        Mockito.verify(mockPublisherService).update(argumentCaptor.capture());

        PublisherIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals(40, sentDto.getId());
        Assertions.assertEquals("Some Publisher 35", sentDto.getName());
        Assertions.assertEquals(1, sentDto.getMagazines().size());
        Assertions.assertTrue(sentDto.getMagazines().contains(
                new MagazineIncomingDto(3, null, null, null)));
    }

    @Test
    void deleteShouldCallDeleteFromService_AndShouldSendId() {
        publisherController.delete(45);

        Mockito.verify(mockPublisherService).delete(45);
    }
}
