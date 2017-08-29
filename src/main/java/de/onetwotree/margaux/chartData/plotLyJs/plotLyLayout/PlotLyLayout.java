package de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.onetwotree.margaux.chartData.json.PlotLy;

import java.util.List;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "autosize",
        "yaxis",
        "paper_bgcolor",
        "plot_bgcolor",
        "title",
        "height",
        "width",
        "legend",
        "xaxis",
        "margin",
        "annotations"
})
public class PlotLyLayout {

    @JsonProperty("autosize")
    private Boolean autosize;
    @JsonProperty("yaxis")
    private Yaxis yaxis;
    @JsonProperty("paper_bgcolor")
    private String paperBgcolor;
    @JsonProperty("plot_bgcolor")
    private String plotBgcolor;
    @JsonProperty("title")
    private String title;
    @JsonProperty("height")
    private Long height;
    @JsonProperty("width")
    private Long width;
    @JsonProperty("legend")
    private Legend legend;
    @JsonProperty("xaxis")
    private Xaxis xaxis;
    @JsonProperty("margin")
    private Margin margin;
    @JsonProperty("annotations")
    private List<Annotation> annotations = null;

    public PlotLyLayout() {
    }

    public Boolean getAutosize() {
        return autosize;
    }

    public void setAutosize(Boolean autosize) {
        this.autosize = autosize;
    }

    public Yaxis getYaxis() {
        return yaxis;
    }

    public void setYaxis(Yaxis yaxis) {
        this.yaxis = yaxis;
    }

    public String getPaperBgcolor() {
        return paperBgcolor;
    }

    public void setPaperBgcolor(String paperBgcolor) {
        this.paperBgcolor = paperBgcolor;
    }

    public String getPlotBgcolor() {
        return plotBgcolor;
    }

    public void setPlotBgcolor(String plotBgcolor) {
        this.plotBgcolor = plotBgcolor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Xaxis getXaxis() {
        return xaxis;
    }

    public void setXaxis(Xaxis xaxis) {
        this.xaxis = xaxis;
    }

    public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }
    /**
     * Default for PieChart
     */
    public static PlotLyLayout simplePlotLyLayout(String title) {
        PlotLyLayout plotLyLayout = new PlotLyLayout();
        plotLyLayout.setAutosize(true);
        plotLyLayout.setTitle(title);
        plotLyLayout.setHeight(Long.valueOf(400));
        return plotLyLayout;
    }
}
