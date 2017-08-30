package de.onetwotree.margaux.controller;


import de.onetwotree.margaux.exception.AddHarvestException;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.exception.PlotResourceException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by SebUndefined on 20/07/17.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    /*@ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }*/
    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
        logger.info("SQLException Occured:: URL="+request.getRequestURL());
        return "database_error";
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(ConstraintViolationException e, RedirectAttributes redirectAttributes) {
        logger.info("Prout#########################################");
        redirectAttributes.addFlashAttribute("alert", e.getCause().getMessage());
        String url = "redirect:/";
        ModelAndView modelAndView = new ModelAndView(url);
        return modelAndView;
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ModelAndView handleItemNotFoundException(ItemNotFoundException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alert", "Item Not Found");
        String url = "redirect:/" + e.getUrl();
        ModelAndView modelAndView = new ModelAndView(url);
        return modelAndView;
    }
    @ExceptionHandler(PlotResourceException.class)
    public ModelAndView handlePlotException(PlotResourceException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alert", e.getErrMessage());
        String url = "redirect:/plot/view/"+ e.getIdPlot() + "/";
        ModelAndView modelAndView = new ModelAndView(url);
        return modelAndView;
    }

    @ExceptionHandler(AddHarvestException.class)
    public ModelAndView handleAddHarvestException(AddHarvestException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alert", e.getErrMessage());
        String url = "redirect:/harvest/add/";
        ModelAndView modelAndView = new ModelAndView(url);
        return modelAndView;
    }
}
