package de.onetwotree.margaux.application;

import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Created by SebUndefined on 19/07/17.
 */
@Component
public class StringToUser implements Converter<String, User> {
    @Autowired
    UserService userService;

    public User convert(String id) {
        System.out.println("###############Testsssssss");
        User user = userService.getUser(Long.valueOf(id));
        System.out.println("###############Testsssssss");
        System.out.println("user email-->" + user.getEmail());
        return user;
    }
}
