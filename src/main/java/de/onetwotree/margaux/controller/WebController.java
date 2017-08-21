package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.UserDao;
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
    private PlotService plotService;
    @Autowired
    private PlotRepository plotRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Plot> plots = plotRepository.findAll();
        model.addAttribute("plots", plots);
        return "home";
    }

}
