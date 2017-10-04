package de.onetwotree.margaux.service;


import de.onetwotree.margaux.dao.RoleRepository;
import de.onetwotree.margaux.dao.UserRepository;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<>(roleRepository.findAll()));
        System.out.println(user.toString());
        userRepository.save(user);
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
