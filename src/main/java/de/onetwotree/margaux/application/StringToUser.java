package de.onetwotree.margaux.application;


import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Created by SebUndefined on 19/07/17.
 */
@Component
public class StringToUser implements Converter<String, UserCustom> {
    @Autowired
    UserService userService;
    @Override
    public UserCustom convert(String id) {
        UserCustom userCustom = userService.getUser(Long.valueOf(id));
        return userCustom;
    }
}
