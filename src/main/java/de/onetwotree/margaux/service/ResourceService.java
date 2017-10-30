package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 04/08/17.
 */
public interface ResourceService {


    List<Resource> findAll();

    @Transactional
    Resource saveResource(Resource resource);

    Resource findOne(Long id);

    Page<Harvest> findHarvestsPaginated(Long idResource, Pageable pageable);

    String findHarvestGroupByPlot(Long idResource);
}
