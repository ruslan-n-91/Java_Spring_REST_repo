package restapp.service;

import restapp.controller.dto.MagazineIncomingDto;
import restapp.controller.dto.MagazineOutgoingDto;

import java.util.List;

public interface MagazineService {
    public List<MagazineOutgoingDto> findAll();

    public MagazineOutgoingDto findOne(int id);

    public void save(MagazineIncomingDto magazineIncomingDto);

    public void update(MagazineIncomingDto magazineIncomingDto);

    public void delete(int id);
}
