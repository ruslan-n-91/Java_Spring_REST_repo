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
import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.AuthorOutgoingDto;
import restapp.controller.mapper.*;
import restapp.model.Author;
import restapp.repository.AuthorRepository;

import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @InjectMocks
    AuthorServiceImpl authorService;

    @Mock
    AuthorRepository mockAuthorRepository;

    @Spy
    AuthorMapper authorMapper = new AuthorMapperImpl();

    @Spy
    CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

    @AfterEach
    void tearDown() {
        Mockito.reset(mockAuthorRepository);
    }

    @Test
    void findAllShouldCallFindAllFromRepository() {
        authorService.findAll();

        Mockito.verify(mockAuthorRepository).findAll();
    }

    @Test
    void findOneShouldCallFindByIdFromRepository_AndShouldReturnValidDto() {
        Author author = new Author(15, "Some Author 15", new HashSet<>());
        Optional<Author> optionalAuthor = Optional.of(author);

        Mockito.doReturn(optionalAuthor).when(mockAuthorRepository).findById(15);

        AuthorOutgoingDto dto = authorService.findOne(15);

        Mockito.verify(mockAuthorRepository).findById(15);
        Assertions.assertEquals(author.getId(), dto.getId());
        Assertions.assertEquals(author.getName(), dto.getName());
        Assertions.assertEquals(author.getBooks().size(), dto.getBooks().size());
    }

    @Test
    void saveShouldCallSaveFromRepository() {
        AuthorIncomingDto dto = new AuthorIncomingDto(null, "Some Author 11", new HashSet<>());
        Author author = new Author(null, "Some Author 11", new HashSet<>());

        authorService.save(dto);

        Mockito.verify(mockAuthorRepository).save(author);
    }

    @Test
    void updateShouldCallSaveFromRepository() {
        AuthorIncomingDto dto = new AuthorIncomingDto(54, "Some Author 11", new HashSet<>());
        Author author = new Author(54, "Some Author 11", new HashSet<>());

        authorService.update(dto);

        Mockito.verify(mockAuthorRepository).save(author);
    }

    @Test
    void deleteShouldCallDeleteByIdFromRepository() {
        authorService.delete(3);

        Mockito.verify(mockAuthorRepository).deleteById(3);
    }
}
