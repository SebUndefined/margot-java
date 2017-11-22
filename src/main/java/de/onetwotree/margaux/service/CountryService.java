package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dto.CountryDTO;
import de.onetwotree.margaux.entity.Country;

import java.util.List;

/**
 * Created by SebUndefined on 21/11/17.
 */
public interface CountryService {
    List<Country> findAll();

    Country saveCountry(CountryDTO countryDTO);
}
