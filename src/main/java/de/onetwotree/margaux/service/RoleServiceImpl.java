package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.RoleRepository;
import de.onetwotree.margaux.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 03/10/17.
 */
@Service("RoleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final
    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findbyId(String idRole) {
        return this.roleRepository.findById(idRole);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
