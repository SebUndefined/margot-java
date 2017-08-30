package de.onetwotree.margaux.exception;

/**
 * Created by SebUndefined on 30/08/17.
 */
public class AddHarvestException extends RuntimeException {

    private String errMessage;

    public AddHarvestException(String message) {
        this.errMessage = message;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
