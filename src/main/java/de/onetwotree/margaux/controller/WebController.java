package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by SebUndefined on 12/07/17.
 */
@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    AlertRepository alertRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {

        List<Alert> alertList = alertRepository.findFirst20ByStatusOrderByDateDesc(AlertStatus.OPEN);
        model.addAttribute("alertList", alertList);
        return "home";
    }

    @GetMapping(value = "/login")
    public String loginAction(Model model) {
        return "login";
    }

}
