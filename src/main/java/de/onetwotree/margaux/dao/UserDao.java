package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.User;

import java.util.List;


/**
 * Created by SebUndefined on 17/07/17.
 */

public interface UserDao {

    public User getUser(long id);

    List<User> getAllUsers();
}
