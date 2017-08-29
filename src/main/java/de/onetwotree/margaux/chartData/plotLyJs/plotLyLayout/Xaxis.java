package de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "tickformat",
        "title",
        "showgrid",
        "range",
        "type",
        "autorange"
})
public class Xaxis {
    @JsonProperty("tickformat")
    private String tickformat;
    @JsonProperty("title")
    private String title;
    @JsonProperty("showgrid")
    private Boolean showgrid;
    @JsonProperty("range")
    private List<Double> range = null;
    @JsonProperty("type")
    private String type;
    @JsonProperty("autorange")
    private Boolean autorange;

    public Xaxis() {
    }

    public String getTickformat() {
        return tickformat;
    }

    public void setTickformat(String tickformat) {
        this.tickformat = tickformat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShowgrid() {
        return showgrid;
    }

    public void setShowgrid(Boolean showgrid) {
        this.showgrid = showgrid;
    }

    public List<Double> getRange() {
        return range;
    }

    public void setRange(List<Double> range) {
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAutorange() {
        return autorange;
    }

    public void setAutorange(Boolean autorange) {
        this.autorange = autorange;
    }
}
