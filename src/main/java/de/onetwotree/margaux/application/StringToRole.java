package de.onetwotree.margaux.application;

import de.onetwotree.margaux.entity.Role;
import de.onetwotree.margaux.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by SebUndefined on 13/12/17.
 */
public class StringToRole implements Converter<String, Role> {
    @Autowired
    RoleService roleService;

    @Override
    public Role convert(String s) {
        return roleService.findbyId(s);
    }
}
