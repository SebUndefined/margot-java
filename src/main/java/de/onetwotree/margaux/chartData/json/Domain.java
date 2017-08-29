package de.onetwotree.margaux.chartData.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x",
        "y"
})
public class Domain {

    @JsonProperty("y")
    private List<Integer> y = new ArrayList<>();
    @JsonProperty("x")
    private List<Integer> x = new ArrayList<>();

    public Domain() {
    }

    public Domain(List<Integer> y, List<Integer> x) {
        this.y = y;
        this.x = x;
    }

    public List<Integer> getY() {
        return y;
    }

    public void setY(List<Integer> y) {
        this.y = y;
    }

    public List<Integer> getX() {
        return x;
    }

    public void setX(List<Integer> x) {
        this.x = x;
    }
}
