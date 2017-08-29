package de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ay",
        "text",
        "font",
        "arrowcolor",
        "ax"
})
public class Annotation {

    @JsonProperty("ay")
    private Long ay;
    @JsonProperty("text")
    private String text;
    @JsonProperty("font")
    private Font font;
    @JsonProperty("arrowcolor")
    private String arrowcolor;
    @JsonProperty("ax")
    private Long ax;

    public Annotation() {
    }

    public Long getAy() {
        return ay;
    }

    public void setAy(Long ay) {
        this.ay = ay;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getArrowcolor() {
        return arrowcolor;
    }

    public void setArrowcolor(String arrowcolor) {
        this.arrowcolor = arrowcolor;
    }

    public Long getAx() {
        return ax;
    }

    public void setAx(Long ax) {
        this.ax = ax;
    }
}
