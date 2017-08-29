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
        "title",
        "range",
        "gridcolor",
        "ticksuffix",
        "type",
        "autorange"
})
public class Yaxis {
    @JsonProperty("title")
    private String title;
    @JsonProperty("range")
    private List<Long> range = null;
    @JsonProperty("gridcolor")
    private String gridcolor;
    @JsonProperty("ticksuffix")
    private String ticksuffix;
    @JsonProperty("type")
    private String type;
    @JsonProperty("autorange")
    private Boolean autorange;

    public Yaxis() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getRange() {
        return range;
    }

    public void setRange(List<Long> range) {
        this.range = range;
    }

    public String getGridcolor() {
        return gridcolor;
    }

    public void setGridcolor(String gridcolor) {
        this.gridcolor = gridcolor;
    }

    public String getTicksuffix() {
        return ticksuffix;
    }

    public void setTicksuffix(String ticksuffix) {
        this.ticksuffix = ticksuffix;
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
    /**
     * Default
     */
}
