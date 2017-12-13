package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.CustomUserDetails;
import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public String homeAction(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {

        List<Alert> alertList = alertRepository.findFirst20ByStatusOrderByDateDesc(AlertStatus.OPEN);
        model.addAttribute("alertList", alertList);

        List<String> roles = activeUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        System.out.println("Here are the roles " + roles);
        if (activeUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            System.out.println("has");
        }
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
