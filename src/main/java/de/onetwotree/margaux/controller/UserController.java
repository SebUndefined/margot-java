package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Role;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.service.RoleService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by SebUndefined on 03/10/17.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/add")
    public String addUserForm(Model model) {
        List<Role> roleList = roleService.findAll();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "User/addUser";
    }

    @PostMapping(value = "/add")
    public String addUserSubmit(@ModelAttribute("project")User user) {
        userService.save(user);
        return "prout";
    }
}
