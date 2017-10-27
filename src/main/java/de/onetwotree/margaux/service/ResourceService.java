package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Resource;
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
}
