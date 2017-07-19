package de.onetwotree.margaux.application;

import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Created by SebUndefined on 19/07/17.
 */
@Component
public class StringToUser implements Converter<String, User> {
    @Autowired
    UserDao userDao;

    public User convert(String id) {
        System.out.println("###############Testsssssss");
        User user = userDao.findUser(Long.valueOf(id));
        System.out.println("###############Testsssssss");
        System.out.println("user email" + user.getEmail());
        return user;
    }
}
