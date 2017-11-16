package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 20/07/17.
 */
public interface HoldingService {


    List<Holding> findAll();

    Page<Holding> findAllPaginated(Pageable pageable);

    Holding findOne(Long idMainCompany);


    @Transactional
    Holding saveHolding(Holding holding);

    @Transactional
    Holding updateHolding(Holding holding);

    Page<Company> findCompaniesPaginated(Long idMainCompany, Pageable pageable);

    Page<Project> findProjectsPaginated(Long idMainCompany, Pageable pageable);

    List<Plot> findPlots(Long idMainCompany);

    Page<Harvest> findHarvestsPaginated(Long idMainCompany, Pageable pageable);

    String findHarvestsByResourcesForGraph(Long idMainCompany, Long idResourceType);
}
