package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 20/07/17.
 */
public interface MainCompanyService {


    List<MainCompany> findAll();

    Page<MainCompany> findAllPaginated(Pageable pageable);

    MainCompany findOne(Long idMainCompany);


    MainCompany saveMainCompany(MainCompany mainCompany);

    @Transactional
    MainCompany updateMainCompany(MainCompany mainCompany);


    Page<Company> findCompaniesPaginated(Long idMainCompany, Pageable pageable);

    Page<Project> findProjectsPaginated(Long idMainCompany, Pageable pageable);

    List<Plot> findPlots(Long idMainCompany);

    Page<Harvest> findHarvestsPaginated(Long idMainCompany, Pageable pageable);

    String findHarvestsByResourcesForGraph(Long idMainCompany, Long idResourceType);
}
