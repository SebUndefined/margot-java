package de.onetwotree.margaux.service;


import de.onetwotree.margaux.dao.RoleRepository;
import de.onetwotree.margaux.dao.UserRepository;
import de.onetwotree.margaux.dto.UserDTO;
import de.onetwotree.margaux.entity.Role;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public User save(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserName(),
                bCryptPasswordEncoder.encode(userDTO.getPassword()),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getBirthdate(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                userDTO.getLocalisation(),
                userDTO.isEnabled(),
                userDTO.isTokenExpired());
        user.setRoles(new ArrayList<>(roleRepository.findAll()));
        user.setPicture(storageService.store(userDTO.getPicture()));
        user = userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
