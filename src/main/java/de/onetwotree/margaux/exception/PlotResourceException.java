package de.onetwotree.margaux.exception;

/**
 * Created by SebUndefined on 30/08/17.
 */
public class PlotResourceException extends RuntimeException {

    private String errMessage;
    private String idPlot;


    public PlotResourceException(String errMessage, String idPlot) {
        this.errMessage = errMessage;
        this.idPlot = idPlot;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }
}
