package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("projectService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

}
