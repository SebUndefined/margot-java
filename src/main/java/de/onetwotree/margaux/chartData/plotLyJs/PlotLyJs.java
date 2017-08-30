package de.onetwotree.margaux.chartData.plotLyJs;

import com.fasterxml.jackson.annotation.*;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout.PlotLyLayout;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "layout"
})
public class PlotLyJs {

    @JsonProperty("layout")
    protected PlotLyLayout layout;
    @JsonIgnore
    protected Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public PlotLyJs() {
    }

    public PlotLyJs(PlotLyLayout layout) {

        this.layout = layout;
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
