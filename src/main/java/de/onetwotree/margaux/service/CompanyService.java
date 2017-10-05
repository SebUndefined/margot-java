package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface CompanyService {

    List<Company> findAll();

    Page<Company> findAllPaginated(Pageable pageable);

    Company findOne(Long idCompany);

    @Transactional
    Company saveCompany(Company mainCompany);

    @Transactional
    Company updateCompany(Company company);

    Page<Project> findProjectsPaginated(Long company, Pageable pageable);

    List<Plot> findPlots(Long company);

    Page<Harvest> findHarvestsPaginated(Long company, Pageable pageable);

    String findHarvestsByResourcesForGraph(Long idCompany, Long idResourceType);
}
