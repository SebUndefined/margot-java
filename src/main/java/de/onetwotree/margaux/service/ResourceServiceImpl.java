package de.onetwotree.margaux.service;


import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by SebUndefined on 04/08/17.
 */
@Service("resourceService")
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    ChartService chartService;

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource findOne(Long idResource) {
        return resourceRepository.findOne(idResource);
    }

    @Override
    @Transactional
    public Resource saveResource(Resource resource) {
        resource = resourceRepository.saveAndFlush(resource);
        return resource;
    }
    @Override
    public Page<Harvest> findHarvestsPaginated(Long idResource, Pageable pageable) {
        return harvestRepository.findAllByResourceId(idResource, pageable);
    }

    @Override
    public String findHarvestGroupByPlot(Long idResource) {
        List<Harvest> harvestList = harvestRepository.findAllByResourceId(idResource);
        Map<String, Map<Integer, BigDecimal>> map;
        map = harvestList.stream()
                .collect(Collectors.groupingBy(t -> t.getPlot().getName(),
                        Collectors.groupingBy(Harvest::getYear,
                                Collectors.mapping(Harvest::getQuantityPerHa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                        )
                ));
        return chartService.buildLineChartHarvestWithYearByPlot(map);
    }

}
