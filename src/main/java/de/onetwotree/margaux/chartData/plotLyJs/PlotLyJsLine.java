package de.onetwotree.margaux.chartData.plotLyJs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.onetwotree.margaux.chartData.plotLyJs.datum.DatumLine;
import de.onetwotree.margaux.chartData.plotLyJs.datum.DatumPie;
import de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout.PlotLyLayout;

import java.util.List;

/**
 * Created by SebUndefined on 30/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class PlotLyJsLine extends PlotLyJs {
    @JsonProperty("data")
    private List<DatumLine> data;

    public PlotLyJsLine() {
        super();
    }

    public PlotLyJsLine(PlotLyLayout layout, List<DatumLine> data) {
        super(layout);
        this.data = data;
    }
    @JsonProperty("data")
    public List<DatumLine> getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(List<DatumLine> data) {
        this.data = data;
    }
}
