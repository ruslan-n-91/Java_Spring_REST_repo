package restapp.service.impl;

import restapp.controller.dto.PublisherIncomingDto;
import restapp.controller.dto.PublisherOutgoingDto;
import restapp.controller.mapper.PublisherMapper;
import restapp.model.Publisher;
import restapp.repository.PublisherRepository;
import restapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapp.controller.mapper.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PublisherServiceImpl implements PublisherService {
    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;
    private final CycleAvoidingMappingContext context;

    @Autowired
    public PublisherServiceImpl(PublisherMapper publisherMapper, PublisherRepository publisherRepository,
                                CycleAvoidingMappingContext context) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
        this.context = context;
    }

    @Override
    public List<PublisherOutgoingDto> findAll() {
        return publisherMapper.mapToPublisherOutgoingDtoList((List<Publisher>) publisherRepository.findAll(), context);
    }

    @Override
    public PublisherOutgoingDto findOne(int id) {
        Optional<Publisher> foundPublisher = publisherRepository.findById(id);
        return foundPublisher.isPresent() ? publisherMapper.mapToPublisherOutgoingDto(foundPublisher.get(), context) : null;
    }

    @Override
    @Transactional
    public void save(PublisherIncomingDto publisherIncomingDto) {
        publisherRepository.save(publisherMapper.mapToPublisher(publisherIncomingDto, context));
    }

    @Override
    @Transactional
    public void update(PublisherIncomingDto publisherIncomingDto) {
        publisherRepository.save(publisherMapper.mapToPublisher(publisherIncomingDto, context));
    }

    @Override
    @Transactional
    public void delete(int id) {
        publisherRepository.deleteById(id);
    }
}
