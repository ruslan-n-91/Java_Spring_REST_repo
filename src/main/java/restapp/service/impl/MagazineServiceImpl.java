package restapp.service.impl;

import restapp.controller.dto.MagazineIncomingDto;
import restapp.controller.dto.MagazineOutgoingDto;
import restapp.controller.mapper.MagazineMapper;
import restapp.model.Magazine;
import restapp.repository.MagazineRepository;
import restapp.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapp.controller.mapper.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MagazineServiceImpl implements MagazineService {
    private final MagazineMapper magazineMapper;
    private final MagazineRepository magazineRepository;
    private final CycleAvoidingMappingContext context;

    @Autowired
    public MagazineServiceImpl(MagazineMapper magazineMapper, MagazineRepository magazineRepository,
                               CycleAvoidingMappingContext context) {
        this.magazineMapper = magazineMapper;
        this.magazineRepository = magazineRepository;
        this.context = context;
    }

    @Override
    public List<MagazineOutgoingDto> findAll() {
        return magazineMapper.mapToMagazineOutgoingDtoList((List<Magazine>) magazineRepository.findAll(), context);
    }

    @Override
    public MagazineOutgoingDto findOne(int id) {
        Optional<Magazine> foundMagazine = magazineRepository.findById(id);
        return foundMagazine.isPresent() ? magazineMapper.mapToMagazineOutgoingDto(foundMagazine.get(), context) : null;
    }

    @Override
    @Transactional
    public void save(MagazineIncomingDto magazineIncomingDto) {
        magazineRepository.save(magazineMapper.mapToMagazine(magazineIncomingDto, context));
    }

    @Override
    @Transactional
    public void update(MagazineIncomingDto magazineIncomingDto) {
        magazineRepository.save(magazineMapper.mapToMagazine(magazineIncomingDto, context));
    }

    @Override
    @Transactional
    public void delete(int id) {
        magazineRepository.deleteById(id);
    }
}
