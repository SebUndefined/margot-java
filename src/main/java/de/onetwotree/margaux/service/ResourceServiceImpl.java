package de.onetwotree.margaux.service;


import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by SebUndefined on 04/08/17.
 */
@Service("resourceService")
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

}
