package de.onetwotree.margaux.chartData.plotLyJs.datum.datumPieFields;

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
        "y",
        "x"
})
public class Domain {

    @JsonProperty("y")
    private List<Long> y = null;
    @JsonProperty("x")
    private List<Long> x = null;

    public Domain() {
    }
    public Domain(List<Long> y, List<Long> x) {
        super();
        this.y = y;
        this.x = x;
    }
    public List<Long> getY() {
        return y;
    }

    public void setY(List<Long> y) {
        this.y = y;
    }

    public List<Long> getX() {
        return x;
    }

    public void setX(List<Long> x) {
        this.x = x;
    }

    /**
     * Default
     */
    public static Domain creatSimpleDomain() {
        List<Long> x = new ArrayList<>();
        List<Long> y = new ArrayList<>();
        x.add(0L);
        x.add(1L);
        y.add(0L);
        y.add(1L);
        Domain domain = new Domain(y, x);
        return domain;

    }
}
