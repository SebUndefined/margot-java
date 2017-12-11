package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.AlertComment;

import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

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

}
