package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Enum.AlertLevel;
import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.dao.MainEntityRepository;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SebUndefined on 27/09/17.
 */
@Controller
@RequestMapping(value = "alert")
public class AlertController {

    private final AlertService alertService;
    private final MainEntityRepository mainEntityRepository;

    @Autowired
    public AlertController(AlertService alertService, MainEntityRepository mainEntityRepository) {
        this.alertService = alertService;
        this.mainEntityRepository = mainEntityRepository;
    }

    @GetMapping(value = "view/{id}/load-comments/")
    public String loadAlertComments(Model model, @PathVariable(name = "id") Alert alert) {

        UserCustom userCustom = (UserCustom)SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        System.out.println("test" + userCustom);

        List<AlertComment> alertComments = alert.getAlertComments()
                .stream()
                .sorted(Comparator.comparing(AlertComment::getDateTime).reversed())
                .collect(Collectors.toList());
        AlertComment alertComment = new AlertComment();
        alertComment.setAlert(alert);
        model.addAttribute("alertComments", alertComments);
        model.addAttribute("newComment", alertComment);

        return "Alert/alertComment :: commentCollection";
    }
    @PostMapping(value = "view/{idAlert}/save-alert-comment/")
    @ResponseBody
    public String saveAlertComment(@ModelAttribute("AlertComment") AlertComment alertComment,
                                   @PathVariable(value = "idAlert") Alert alert,
                                   @AuthenticationPrincipal UserCustom activeUser) {
        System.out.println("Here " + alertComment.getId());
        System.out.println("Here " + alertComment.getCommentContent());
        alertComment.setDateTime(LocalDateTime.now());
        alertComment.setAlert(alert);
        UserCustom userCustom = activeUser;
        alertComment.setAuthor(userCustom);
        alert.getAlertComments().add(alertComment);
        alertService.saveAlert(alert);
        return "done";
    }


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
