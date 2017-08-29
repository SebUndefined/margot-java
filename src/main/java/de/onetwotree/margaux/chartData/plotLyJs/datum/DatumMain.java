package de.onetwotree.margaux.chartData.plotLyJs.datum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "mode",
        "type",
        "name"
})
public class DatumMain {
    @JsonProperty("uid")
    protected String uid;
    @JsonProperty("showlegend")
    protected Boolean showlegend;
    @JsonProperty("mode")
    protected String mode;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("name")
    protected String name;

    public DatumMain() {
    }

    public DatumMain(String uid, Boolean showlegend, String mode, String type, String name) {
        this.uid = uid;
        this.showlegend = showlegend;
        this.mode = mode;
        this.type = type;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getShowlegend() {
        return showlegend;
    }

    public void setShowlegend(Boolean showlegend) {
        this.showlegend = showlegend;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
