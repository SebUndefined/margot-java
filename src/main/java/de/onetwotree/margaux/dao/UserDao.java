package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.User;


/**
 * Created by SebUndefined on 17/07/17.
 */

public interface UserDao {

    public void addUser(User user);


    User findUser(Long id);
}
