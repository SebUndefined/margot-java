package de.onetwotree.margaux.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by SebUndefined on 04/08/17.
 */
@Service("resourceService")
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {


}
