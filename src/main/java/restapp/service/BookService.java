package restapp.service;

import restapp.controller.dto.BookIncomingDto;
import restapp.controller.dto.BookOutgoingDto;

import java.util.List;

public interface BookService {
    public List<BookOutgoingDto> findAll();

    public BookOutgoingDto findOne(int id);

    public void save(BookIncomingDto bookIncomingDto);

    public void update(int id, BookIncomingDto bookIncomingDto);

    public void delete(int id);
}
