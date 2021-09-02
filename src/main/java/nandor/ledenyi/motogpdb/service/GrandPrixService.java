package nandor.ledenyi.motogpdb.service;

import nandor.ledenyi.motogpdb.mapper.GrandPrixMapper;
import nandor.ledenyi.motogpdb.mapper.RiderMapper;
import nandor.ledenyi.motogpdb.model.dto.GrandPrixDto;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.repository.CountryRepository;
import nandor.ledenyi.motogpdb.repository.GrandPrixRepository;
import nandor.ledenyi.motogpdb.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrandPrixService {

    @Autowired
    private GrandPrixRepository grandPrixRepository;
    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private GrandPrixMapper grandPrixMapper;
    @Autowired
    private RiderMapper riderMapper;

    public List<GrandPrixDto> findAll() {
        List<GrandPrixDto> grandPrixs = new ArrayList<>();
        grandPrixRepository.findAll().forEach(grandPrix -> grandPrixs.add(grandPrixMapper.convertToDto(grandPrix)));
        if (grandPrixs.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return grandPrixs;
    }

    public GrandPrixDto findById(Long id) {
        return grandPrixMapper.convertToDto(grandPrixRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    public void save(GrandPrixDto grandPrixDto) {
        grandPrixRepository.save(grandPrixMapper.convertToEntity(
                grandPrixDto,
                countryRepository.findById(grandPrixDto.getCountryId()).orElseThrow(DatabaseIntegrityViolationException::new),
                riderRepository.findById(grandPrixDto.getWinningRiderId()).orElseThrow(DatabaseIntegrityViolationException::new)
        ));
    }

    public void deleteById(Long id) {
        if (grandPrixRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException();
        }
        grandPrixRepository.deleteById(id);
    }

    public void updateById(GrandPrixDto grandPrixDto, Long id) {
        grandPrixDto.setId(id);
        grandPrixRepository.save(grandPrixMapper.convertToEntity(
                grandPrixDto,
                countryRepository.findById(grandPrixDto.getCountryId()).orElseThrow(DatabaseIntegrityViolationException::new),
                riderRepository.findById(grandPrixDto.getWinningRiderId()).orElseThrow(DatabaseIntegrityViolationException::new)
        ));
    }

    public RiderDto getWinningRiderByGrandPrixId(Long id) {
        if (grandPrixRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException();
        }
        return riderMapper.convertToDto(grandPrixRepository.getWinningRiderByGrandPrixId(id));
    }
}
