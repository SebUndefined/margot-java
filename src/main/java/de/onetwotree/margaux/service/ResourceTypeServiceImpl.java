package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 05/10/17.
 */
@Service("resourceTypeService")
@Transactional(readOnly = true)
public class ResourceTypeServiceImpl implements ResourceTypeService {

    private final
    ResourceTypeRepository resourceTypeRepository;

    @Autowired
    public ResourceTypeServiceImpl(ResourceTypeRepository resourceTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
    }

    @Override
    public List<ResourceType> findAll() {
        return resourceTypeRepository.findAll();
    }
}
