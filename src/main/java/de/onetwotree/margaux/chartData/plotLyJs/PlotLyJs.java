package de.onetwotree.margaux.chartData.plotLyJs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout.PlotLyLayout;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SebUndefined on 29/08/17.
 */
public class PlotLyJs {
    @JsonProperty("data")
    private List<Datum> data = null;
    @JsonProperty("layout")
    private PlotLyLayout layout;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public PlotLyJs() {
    }

    public PlotLyJs(List<Datum> data, PlotLyLayout layout) {
        this.data = data;
        this.layout = layout;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    public PlotLyJs withData(List<Datum> data) {
        this.data = data;
        return this;
    }


    public PlotLyLayout getLayout() {
        return layout;
    }

    public void setLayout(PlotLyLayout layout) {
        this.layout = layout;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public PlotLyJs withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
