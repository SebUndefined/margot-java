package de.onetwotree.margaux.service;


import de.onetwotree.margaux.dao.RoleRepository;
import de.onetwotree.margaux.dao.UserRepository;
import de.onetwotree.margaux.dto.UserDTO;
import de.onetwotree.margaux.entity.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Service("userService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StorageService storageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StorageService storageService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.storageService = storageService;
    }

    @Override
    public Page<UserCustom> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public String save(UserDTO userDTO) {
        //UserCustom userCustom = new UserCustom();
//        userCustom.setRoles(new ArrayList<>(roleRepository.findAll()));
//        userCustom.setPicture(storageService.store(userDTO.getPicture()));
//        userCustom = userRepository.saveAndFlush(userCustom);
        return "test";
    }

    @Override
    public UserCustom findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public UserCustom getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<UserCustom> getAllUsers() {
        return userRepository.findAll();
    }
}
