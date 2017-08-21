package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.MainCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SebUndefined on 20/07/17.
 */
@Service("mainCompanyService")
@Transactional(readOnly = true)
public class MainCompanyServiceImpl implements MainCompanyService {
    @Autowired
    private MainCompanyRepository mainCompanyRepository;


}
