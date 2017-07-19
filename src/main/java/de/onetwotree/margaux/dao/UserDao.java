package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.User;

import java.util.List;


/**
 * Created by SebUndefined on 17/07/17.
 */

public interface UserDao {

    public void addUser(User user);


    List<User> findAll();

    User findUser(Long id);
}
