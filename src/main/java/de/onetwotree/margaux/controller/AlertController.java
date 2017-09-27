package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.dao.MainEntityRepository;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.MainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

/**
 * Created by SebUndefined on 27/09/17.
 */
@Controller
@RequestMapping(value = "alert")
public class AlertController {

    @Autowired
    AlertRepository alertRepository;
    @Autowired
    MainEntityRepository mainEntityRepository;

    @GetMapping(value = "add/{mainEntityId}")
    public String addAlertForm(Model model, @PathVariable(name = "mainEntityId") MainEntity mainEntity) {
        Alert alert = new Alert();
        alert.setMainEntity(mainEntity);
        model.addAttribute("alert", alert);
        return "Alert/addAlert";
    }
    @PostMapping(value = "add/{mainEntityId}")
    public String addAlertSubmit(@PathVariable(name = "mainEntityId") MainEntity mainEntity, @ModelAttribute("Alert") @Valid Alert alert,
                               BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!result.hasErrors()) {
            try {
                alert.setMainEntity(mainEntity);
                mainEntity.getAlerts().add(alert);
                //mainEntityRepository.save(mainEntity);
            } catch (ConstraintViolationException e) {
                e.printStackTrace();
            }
        }
        System.out.println(mainEntity.getClass());
        return "redirect:" + request.getRequestURI();
    }
}
