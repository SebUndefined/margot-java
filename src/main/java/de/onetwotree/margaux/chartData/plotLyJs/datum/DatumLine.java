package de.onetwotree.margaux.chartData.plotLyJs.datum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 01/09/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "y",
        "x"

})
public class DatumLine extends DatumMain {

    @JsonProperty("y")
    private List<String> y = new ArrayList<>();
    @JsonProperty("x")
    private List<String> x = new ArrayList<>();


    public DatumLine() {
    }

    public DatumLine(String uid, Boolean showlegend, String mode, String type, String name, List<String> y, List<String> x) {
        super(uid, showlegend, mode, type, name);
        this.y = y;
        this.x = x;
    }

    public List<String> getY() {
        return y;
    }

    public void setY(List<String> y) {
        this.y = y;
    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }
}
