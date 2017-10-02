package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Enum.AlertLevel;
import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.dao.MainEntityRepository;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.MainEntity;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by SebUndefined on 27/09/17.
 */
@Controller
@RequestMapping(value = "alert")
public class AlertController {

    @Autowired
    AlertService alertService;
    @Autowired
    MainEntityRepository mainEntityRepository;

    @GetMapping(value = "add/{mainEntityId}")
    public String addAlertForm(Model model, @PathVariable(name = "mainEntityId") Long idMainEntity) throws ItemNotFoundException {
        Alert alert = new Alert();
        MainEntity mainEntity = mainEntityRepository.findOne(idMainEntity);
        if (mainEntity == null) {
            throw new ItemNotFoundException(Long.valueOf(idMainEntity), "alert/");
        }
        alert.setMainEntity(mainEntity);
        model.addAttribute("alertNew", alert);
        return "Alert/addAlert";
    }
    @PostMapping(value = "add/{mainEntityId}")
    public String addAlertSubmit(@PathVariable(name = "mainEntityId") MainEntity mainEntity, @ModelAttribute("Alert") @Valid Alert alert,
                               BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String redirectURL = "/";
        if (!result.hasErrors()) {
            redirectURL = alertService.addAlertToMainEntity(mainEntity, alert);
        }
        else {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:" + request.getRequestURI();
        }
        redirectAttributes.addFlashAttribute("info", "Alert Saved !");

        return "redirect:" + redirectURL;
    }
}
