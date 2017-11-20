package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Country;
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

/**
 * Created by SebUndefined on 20/11/17.
 */
@Controller
@RequestMapping(value = "country")
public class CountryController {

    @GetMapping(value = "/add")
    public String addCountryForm (Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "Country/editCountry";
    }
    @PostMapping (value = "/add")
    public String addCountrySubmit (Model model, @Valid Country country,
                                    BindingResult result, RedirectAttributes redirectAttributes) {
        System.out.println(country.getFlag());
        //storageService.store(country.getFlag());
        return "redirect:/country/add/";
    }
}
