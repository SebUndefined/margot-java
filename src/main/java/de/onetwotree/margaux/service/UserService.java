package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.User;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface UserService {


    User getUser(Long id);

    List<User> getAllUsers();
}
