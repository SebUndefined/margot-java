package de.onetwotree.margaux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by SebUndefined on 25/08/17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such item")
public class ItemNotFoundException extends Throwable {
    private Long idItem;
    private String url;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ItemNotFoundException(Long idItem, String url) {

        this.idItem = idItem;
        this.url = url;

    }
}
