package de.onetwotree.margaux.application;

import de.onetwotree.margaux.dao.MainCompanyRepository;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.service.MainCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by SebUndefined on 21/07/17.
 */
public class StringToMainCompany implements Converter<String, MainCompany> {

    @Autowired
    MainCompanyRepository mainCompanyRepository;

    public MainCompany convert(String id) {
        MainCompany mainCompany = mainCompanyRepository.findOne(Long.valueOf(id));
        System.out.println("###############String to main company loaded");
        System.out.println("user email-->" + mainCompany.getName());
        return mainCompany;
    }
}
