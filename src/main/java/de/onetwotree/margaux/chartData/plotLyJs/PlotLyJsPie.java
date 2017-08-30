package de.onetwotree.margaux.chartData.plotLyJs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
public class PlotLyJsPie extends PlotLyJs {
    @JsonProperty("data")
    private List<DatumPie> data;

    public PlotLyJsPie() {
        super();
    }

    public PlotLyJsPie(PlotLyLayout layout, List<DatumPie> data) {
        super(layout);
        this.data = data;
    }
    @JsonProperty("data")
    public List<DatumPie> getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(List<DatumPie> data) {
        this.data = data;
    }
}
