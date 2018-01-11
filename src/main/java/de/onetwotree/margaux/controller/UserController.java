package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dto.UserDTO;
import de.onetwotree.margaux.entity.Role;
import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.service.RoleService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<UserCustom> userPage = userService.findAll(pageable);
        System.out.println(userPage.getTotalElements());
        model.addAttribute("users", userPage);
        return "User/user";
    }
    @GetMapping(value = "/view/{id}")
    public String viewUser(@PathVariable(value = "id") UserCustom userCustom,  Model model) {
        model.addAttribute("userCustom", userCustom);
        return "User/viewUser";
    }

    @GetMapping(value = "/add")
    public String addUserForm(Model model) {
        List<Role> roleList = roleService.findAll();
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("roleList", roleList);
        return "User/addUser";
    }

    @PostMapping(value = "/add")
    public String addUserSubmit(@ModelAttribute("UserDTO") @Valid UserDTO userDTO,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("alerts", result.getAllErrors());
            return "redirect:/user/add/";
        }
        UserCustom userCustom = userService.save(userDTO);
        if (userCustom.getId() != null) {
            redirectAttributes.addFlashAttribute("info", "User " + userCustom.getUsername() + " has been saved !");
        }
        return "redirect:/user/view/" + userCustom.getId();
    }
}
