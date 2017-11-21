package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dto.CountryDTO;

import de.onetwotree.margaux.entity.Country;
import de.onetwotree.margaux.service.CountryService;
import de.onetwotree.margaux.service.StorageService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.io.File;

/**
 * Created by SebUndefined on 20/11/17.
 */
@Controller
@RequestMapping(value = "country")
public class CountryController {
    private final
    StorageService storageService;
    private final CountryService countryService;



    @Autowired
    public CountryController(StorageService storageService, CountryService countryService) {
        this.storageService = storageService;
        this.countryService = countryService;
    }

    @GetMapping(value = "/add")
    public String addCountryForm (Model model) {
        CountryDTO country = new CountryDTO();
        model.addAttribute("country", country);
        return "Country/editCountry";
    }
    @PostMapping (value = "/add")
    public String addCountrySubmit (Model model, @Valid CountryDTO countryDTO,
                                    BindingResult result, RedirectAttributes redirectAttributes) {
        Country country = countryService.saveCountry(countryDTO);
        return "redirect:/country/add/";
    }
}
