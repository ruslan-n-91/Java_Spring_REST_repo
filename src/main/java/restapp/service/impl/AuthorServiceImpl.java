package restapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.AuthorOutgoingDto;
import restapp.controller.mapper.AuthorMapper;
import restapp.controller.mapper.CycleAvoidingMappingContext;
import restapp.model.Author;
import restapp.repository.AuthorRepository;
import restapp.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final CycleAvoidingMappingContext context;

    @Autowired
    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository,
                             CycleAvoidingMappingContext context) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
        this.context = context;
    }

    @Override
    public List<AuthorOutgoingDto> findAll() {
        return authorMapper.mapToAuthorOutgoingDtoList((List<Author>) authorRepository.findAll(), context);
    }

    @Override
    public AuthorOutgoingDto findOne(int id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        return foundAuthor.isPresent() ? authorMapper.mapToAuthorOutgoingDto(foundAuthor.get(), context) : null;
    }

    @Override
    @Transactional
    public void save(AuthorIncomingDto authorIncomingDto) {
        authorRepository.save(authorMapper.mapToAuthor(authorIncomingDto, context));
    }

    @Override
    @Transactional
    public void update(AuthorIncomingDto authorIncomingDto) {
        authorRepository.save(authorMapper.mapToAuthor(authorIncomingDto, context));
    }

    @Override
    @Transactional
    public void delete(int id) {
        authorRepository.deleteById(id);
    }
}
