package de.onetwotree.margaux.service;


import de.onetwotree.margaux.dao.UserRepository;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Service("userService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
