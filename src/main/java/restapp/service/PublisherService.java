package restapp.service;

import restapp.controller.dto.PublisherIncomingDto;
import restapp.controller.dto.PublisherOutgoingDto;

import java.util.List;

public interface PublisherService {
    public List<PublisherOutgoingDto> findAll();

    public PublisherOutgoingDto findOne(int id);

    public void save(PublisherIncomingDto publisherIncomingDto);

    public void update(PublisherIncomingDto publisherIncomingDto);

    public void delete(int id);
}
