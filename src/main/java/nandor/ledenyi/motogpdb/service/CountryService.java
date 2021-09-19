package nandor.ledenyi.motogpdb.service;

import nandor.ledenyi.motogpdb.exception.DataNotFoundException;
import nandor.ledenyi.motogpdb.exception.EmptyDatabaseException;
import nandor.ledenyi.motogpdb.mapper.CountryMapper;
import nandor.ledenyi.motogpdb.mapper.GrandPrixMapper;
import nandor.ledenyi.motogpdb.mapper.RiderMapper;
import nandor.ledenyi.motogpdb.model.dto.CountryDto;
import nandor.ledenyi.motogpdb.model.dto.GrandPrixDto;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import nandor.ledenyi.motogpdb.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private GrandPrixMapper grandPrixMapper;
    @Autowired
    private RiderMapper riderMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public List<CountryDto> findAll() {
        List<CountryDto> countries = new ArrayList<>();
        countryRepository.findAll().forEach(country -> countries.add(countryMapper.convertToDto(country)));
        if (countries.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return countries;
    }

    public CountryDto findById(Long id) {
        return countryMapper.convertToDto(countryRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    public void save(CountryDto countryDto) {
        countryRepository.save(countryMapper.convertToEntity(countryDto));
    }

    public void deleteById(Long id) {
        if (countryRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException();
        }
        countryRepository.deleteById(id);
    }

    public void updateById(CountryDto countryDto, Long id) {
        countryDto.setId(id);
        countryRepository.save(countryMapper.convertToEntity(countryDto));
    }

    public List<GrandPrixDto> getGrandPrixsFromCountry(Long id) {
        List<GrandPrixDto> grandPrixs = new ArrayList<>();
        if (countryRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException();
        }
        countryRepository.getGrandPrixsFromCountry(id).forEach(grandPrix -> grandPrixs.add(grandPrixMapper.convertToDto(grandPrix)));
        if (grandPrixs.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return grandPrixs;
    }

    public List<RiderDto> getRidersFromCountry(Long id) {
        List<RiderDto> riders = new ArrayList<>();
        if (countryRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException();
        }
        countryRepository.getRidersFromCountry(id).forEach(rider -> riders.add(riderMapper.convertToDto(rider)));
        if (riders.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return riders;
    }
}
