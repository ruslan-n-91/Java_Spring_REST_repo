package restapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapp.controller.dto.BookIncomingDto;
import restapp.controller.dto.BookOutgoingDto;
import restapp.mapper.BookMapper;
import restapp.mapper.CycleAvoidingMappingContext;
import restapp.model.Book;
import restapp.repository.BookRepository;
import restapp.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final CycleAvoidingMappingContext context;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository,
                           CycleAvoidingMappingContext context) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.context = context;
    }

    @Override
    public List<BookOutgoingDto> findAll() {
        return bookMapper.mapToBookOutgoingDtoList((List<Book>) bookRepository.findAll(), context);
    }

    @Override
    public BookOutgoingDto findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.isPresent() ? bookMapper.mapToBookOutgoingDto(foundBook.get(), context) : null;
    }

    @Override
    @Transactional
    public void save(BookIncomingDto bookIncomingDto) {
        bookRepository.save(bookMapper.mapToBook(bookIncomingDto, context));
    }

    @Override
    @Transactional
    public void update(int id, BookIncomingDto bookIncomingDto) {
        bookIncomingDto.setId(id);
        bookRepository.save(bookMapper.mapToBook(bookIncomingDto, context));
    }

    @Override
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
