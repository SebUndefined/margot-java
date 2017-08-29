package de.onetwotree.margaux.chartData.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "width",
        "autosize",
        "title",
        "height",
        "paper_bgcolor",
        "plot_bgcolor"
})
public class Layout {

    @JsonProperty("width")
    private Long width;
    @JsonProperty("autosize")
    private Boolean autosize;
    @JsonProperty("title")
    private String title;
    @JsonProperty("height")
    private Long height;
    @JsonProperty("paper_bgcolor")
    private String paperBgColor;
    @JsonProperty("plot_bgcolor")
    private String plotBgColor;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Layout() {
        this.autosize = true;
        this.title = "Graph";
        this.height = Long.valueOf(400);
        this.plotBgColor = "rgb(242, 242, 242)";
        this.paperBgColor = "rgb(242, 242, 242)";
    }
    public Layout(String title) {
        this.autosize = true;
        this.title = title;
        this.height = Long.valueOf(400);
    }

    /**
     *
     * @param title
     * @param height
     * @param width
     * @param autosize
     */
    public Layout(Long width, Boolean autosize, String title, Long height) {
        super();
        this.width = width;
        this.autosize = autosize;
        this.title = title;
        this.height = height;
    }

    @JsonProperty("width")
    public Long getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Long width) {
        this.width = width;
    }

    public Layout withWidth(Long width) {
        this.width = width;
        return this;
    }

    @JsonProperty("autosize")
    public Boolean getAutosize() {
        return autosize;
    }

    @JsonProperty("autosize")
    public void setAutosize(Boolean autosize) {
        this.autosize = autosize;
    }

    public Layout withAutosize(Boolean autosize) {
        this.autosize = autosize;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Layout withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("height")
    public Long getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Long height) {
        this.height = height;
    }

    public String getPaperBgColor() {
        return paperBgColor;
    }

    public void setPaperBgColor(String paperBgColor) {
        this.paperBgColor = paperBgColor;
    }

    public String getPlotBgColor() {
        return plotBgColor;
    }

    public void setPlotBgColor(String plotBgColor) {
        this.plotBgColor = plotBgColor;
    }

    public Layout withHeight(Long height) {
        this.height = height;
        return this;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Layout withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}