package de.onetwotree.margaux.appConfig;

import de.onetwotree.margaux.controller.GlobalDefaultExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by SebUndefined on 11/01/18.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    public CustomAccessDeniedHandler() {
    }

    public CustomAccessDeniedHandler(String errorPage) {
        this.errorPage = errorPage;
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exc) throws IOException, ServletException {
            //httpServletResponse.sendRedirect(errorPage);{
            Authentication auth
                    = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                logger.warn("User: " + auth.getName()
                        + " attempted to access the protected URL: "
                        + request.getRequestURI());
            }

            response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
}
