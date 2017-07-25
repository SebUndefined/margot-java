package de.onetwotree.margaux.application;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
/**
 * Created by SebUndefined on 25/07/17.
 */
public class StringToCompany implements Converter<String, Company> {

    @Autowired
    CompanyService CompanyService;

    public Company convert(String id) {
        Company company = CompanyService.getCompany(Long.valueOf(id));
        System.out.println("###############String to company loaded");
        System.out.println("user email-->" + company.getName());
        return company;
    }
}
