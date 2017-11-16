package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Role;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.service.RoleService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by SebUndefined on 03/10/17.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    private final
    RoleService roleService;
    private final
    UserService userService;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String userIndex(Model model,
                            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<User> userPage = userService.findAll(pageRequest);
        model.addAttribute("users", userPage);
        return "User/user";
    }

    @GetMapping(value = "/add")
    public String addUserForm(Model model) {
        List<Role> roleList = roleService.findAll();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "User/addUser";
    }

    @PostMapping(value = "/add")
    public String addUserSubmit(@ModelAttribute("user")User user) {
        userService.save(user);
        return "prout";
    }
}
