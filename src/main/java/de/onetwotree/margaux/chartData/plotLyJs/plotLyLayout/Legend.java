package de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "y",
        "x",
        "traceorder",
        "bgcolor"
})
public class Legend {
    @JsonProperty("y")
    private Double y;
    @JsonProperty("x")
    private Double x;
    @JsonProperty("traceorder")
    private String traceorder;
    @JsonProperty("bgcolor")
    private String bgcolor;

    public Legend() {
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public String getTraceorder() {
        return traceorder;
    }

    public void setTraceorder(String traceorder) {
        this.traceorder = traceorder;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }
}
