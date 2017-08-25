package de.onetwotree.margaux.controllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by SebUndefined on 25/08/17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such item")
public class ItemNotFoundException extends Throwable {
    private String message;

    public ItemNotFoundException(Long idCompany) {
        this.message = this.getMessage();
    }
}
