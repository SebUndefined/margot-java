package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.User;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface UserService {

    User getUser(long id);
    List<User> getAllUsers();
}
