package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.CompanyRepository;
import de.onetwotree.margaux.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Service("companyService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
}
