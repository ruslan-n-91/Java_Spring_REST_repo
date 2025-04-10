package restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import restapp.service.MagazineService;

@ExtendWith(MockitoExtension.class)
class MagazineControllerTest {
    @InjectMocks
    private MagazineController magazineController;

    @Mock
    private MagazineService mockMagazineService;

    @AfterEach
    void tearDown() {
        Mockito.reset(mockMagazineService);
    }

    @Test
    void indexShouldCallFindAllFromService() throws JsonProcessingException {
        magazineController.index();

        Mockito.verify(mockMagazineService).findAll();
    }

    @Test
    void showShouldCallFindOneFromService() throws JsonProcessingException {
        magazineController.show(53);

        Mockito.verify(mockMagazineService).findOne(53);
    }

    @Test
    void createShouldCallSaveFromService_AndShouldSendValidDto() throws JsonProcessingException {
        PublisherIncomingDto publisherIncomingDto1 = new PublisherIncomingDto(1, null, null);
        MagazineIncomingDto magazineIncomingDto = new MagazineIncomingDto(null, "Some Magazine 35",
                20, publisherIncomingDto1);

        magazineController.create(magazineIncomingDto);

        ArgumentCaptor<MagazineIncomingDto> argumentCaptor = ArgumentCaptor.forClass(MagazineIncomingDto.class);
        Mockito.verify(mockMagazineService).save(argumentCaptor.capture());

        MagazineIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals("Some Magazine 35", sentDto.getTitle());
        Assertions.assertEquals(20, sentDto.getQuantity());
        Assertions.assertEquals(new PublisherIncomingDto(1, null, null),
                sentDto.getPublisher());
    }

    @Test
    void updateShouldCallUpdateFromService_AndShouldSendValidDto() throws JsonProcessingException {
        PublisherIncomingDto publisherIncomingDto1 = new PublisherIncomingDto(3, null, null);
        MagazineIncomingDto magazineIncomingDto = new MagazineIncomingDto(null, "Some Magazine 35",
                20, publisherIncomingDto1);

        magazineController.update(magazineIncomingDto, 40);

        ArgumentCaptor<MagazineIncomingDto> argumentCaptor = ArgumentCaptor.forClass(MagazineIncomingDto.class);
        Mockito.verify(mockMagazineService).update(argumentCaptor.capture());

        MagazineIncomingDto sentDto = argumentCaptor.getValue();

        Assertions.assertEquals(40, sentDto.getId());
        Assertions.assertEquals("Some Magazine 35", sentDto.getTitle());
        Assertions.assertEquals(20, sentDto.getQuantity());
        Assertions.assertEquals(new PublisherIncomingDto(3, null, null),
                sentDto.getPublisher());
    }

    @Test
    void deleteShouldCallDeleteFromService_AndShouldSendId() throws JsonProcessingException {
        magazineController.delete(45);

        Mockito.verify(mockMagazineService).delete(45);
    }
}
