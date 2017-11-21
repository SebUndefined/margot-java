package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dto.CountryDTO;
import de.onetwotree.margaux.entity.Country;

/**
 * Created by SebUndefined on 21/11/17.
 */
public interface CountryService {
    Country saveCountry(CountryDTO countryDTO);
}
