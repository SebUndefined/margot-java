package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.AlertComment;
import de.onetwotree.margaux.entity.CustomUserDetails;
import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by SebUndefined on 06/12/17.
 */
@Controller
@RequestMapping(value = "alert-comment")
public class AlertCommentController {
    private final
    AlertService alertService;

    @Autowired
    public AlertCommentController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping(value = "view/{id}/save-alert-comment/")
    @ResponseBody
    public String saveAlertComment(@PathVariable(value = "id") Alert alert, @ModelAttribute AlertComment alertComment, Principal principal) {
        System.out.println("On est là...");
        alertComment.setAlert(alert);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(customUserDetails.getUserCustom().getPassword());

        System.out.println(principal.toString());
        alertComment.setAuthor(customUserDetails.getUserCustom());
        alert.getAlertComments().add(alertComment);
        alertService.saveAlert(alert);
        return "Done";
    }
}
