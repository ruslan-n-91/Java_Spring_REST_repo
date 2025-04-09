package restapp.service;

import restapp.controller.dto.AuthorIncomingDto;
import restapp.controller.dto.AuthorOutgoingDto;

import java.util.List;

public interface AuthorService {
    public List<AuthorOutgoingDto> findAll();

    public AuthorOutgoingDto findOne(int id);

    public void save(AuthorIncomingDto authorIncomingDto);

    public void update(AuthorIncomingDto authorIncomingDto);

    public void delete(int id);
}
