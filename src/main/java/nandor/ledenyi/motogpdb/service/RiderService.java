package nandor.ledenyi.motogpdb.service;

import nandor.ledenyi.motogpdb.exception.DataNotFoundException;
import nandor.ledenyi.motogpdb.exception.DatabaseIntegrityViolationException;
import nandor.ledenyi.motogpdb.exception.EmptyDatabaseException;
import nandor.ledenyi.motogpdb.mapper.RiderMapper;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.repository.CountryRepository;
import nandor.ledenyi.motogpdb.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RiderMapper riderMapper;

    public List<RiderDto> findAll() {
        List<RiderDto> riders = new ArrayList<>();
        riderRepository.findAll().forEach(rider -> riders.add(riderMapper.convertToDto(rider)));
        if (riders.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return riders;
    }

    public RiderDto findById(Long id) {
        return riderMapper.convertToDto(riderRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    public void save(RiderDto riderDto) {
        riderRepository.save(riderMapper.convertToEntity(
                riderDto,
                countryRepository.findById(riderDto.getCountryId()).orElseThrow(DatabaseIntegrityViolationException::new)
        ));
    }

    public void deleteById(Long id) {
        if (riderRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException();
        }
        riderRepository.deleteById(id);
    }

    public void updateById(RiderDto riderDto, Long id) {
        riderDto.setId(id);
        riderRepository.save(riderMapper.convertToEntity(
                riderDto,
                countryRepository.findById(riderDto.getCountryId()).orElseThrow(DatabaseIntegrityViolationException::new)
        ));
    }
}
