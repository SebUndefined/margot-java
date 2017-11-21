package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.CountryRepository;
import de.onetwotree.margaux.dto.CountryDTO;
import de.onetwotree.margaux.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Country saveCountry(CountryDTO countryDTO) {
        Country country = new Country();

        storageService.store(countryDTO.getFlag());
        return country;
    }
}
