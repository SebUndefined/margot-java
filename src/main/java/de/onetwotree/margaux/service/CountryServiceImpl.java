package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.CountryRepository;
import de.onetwotree.margaux.dto.CountryDTO;
import de.onetwotree.margaux.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SebUndefined on 21/11/17.
 */
@Service("CountryService")
public class CountryServiceImpl implements CountryService {

    private final StorageService storageService;
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(StorageService storageService, CountryRepository countryRepository) {
        this.storageService = storageService;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    @Override
    public Country saveCountry(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setFlag(storageService.store(countryDTO.getFlag()));
        country = countryRepository.saveAndFlush(country);
        return country;
    }
}
