package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.User;
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
    private UserDao userDao;

    @Autowired
    private PlotService plotService;

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Plot> plots = plotService.getAllPlot();
        model.addAttribute("plots", plots);
        return "home";
    }
    @RequestMapping("/test")
    public String testAction() {
        System.out.println("prout");
        return "test";
    }

}
