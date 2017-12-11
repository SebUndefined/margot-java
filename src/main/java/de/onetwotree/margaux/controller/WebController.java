package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by SebUndefined on 12/07/17.
 */
@Controller
@RequestMapping("/")
public class WebController {

    private final
    AlertRepository alertRepository;

    private final
    UserService userService;

    @Autowired
    public WebController(AlertRepository alertRepository, UserService userService) {
        this.alertRepository = alertRepository;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String homeAction(Model model, HttpServletRequest request) {

        List<Alert> alertList = alertRepository.findFirst20ByStatusOrderByDateDesc(AlertStatus.OPEN);
        model.addAttribute("alertList", alertList);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        if (request.isUserInRole("ROLE_USER")) {
            System.out.println("Has User Role");
        }
        System.out.println("Here are the roles " + roles);
        return "home";
    }

    @GetMapping(value = "/login")
    public String loginAction(Model model) {
        return "login";
    }

    @GetMapping(value = "/profile")
    public String profileAction(Model model, Principal principal)
    {
        System.out.println(principal.getName());
        UserCustom userCustom = userService.findByUsername(principal.getName());
        model.addAttribute("userCustom", userCustom);
        return "User/profile";
    }

}
