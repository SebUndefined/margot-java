package de.onetwotree.margaux.chartData.plotLyJs.datum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.plotLyJs.datum.datumPieFields.Domain;
import de.onetwotree.margaux.chartData.plotLyJs.datum.datumPieFields.Marker;

import java.util.*;

/**
 * Created by SebUndefined on 29/08/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pull",
        "domain",
        "labels",
        "values",
        "marker",
        "textinfo",
        "hole"
})
public class DatumPie extends DatumMain {

    @JsonProperty("pull")
    private Long pull;
    @JsonProperty("domain")
    private Domain domain;
    @JsonProperty("labels")
    private List<String> labels = null;
    @JsonProperty("values")
    private List<String> values = null;
    @JsonProperty("marker")
    private Marker marker;
    @JsonProperty("textinfo")
    private String textinfo;
    @JsonProperty("hole")
    private Double hole;

    public DatumPie() {
        super();
    }

    public DatumPie(Long pull, Domain domain, List<String> labels,
                    List<String> values, Marker marker, String textinfo, Double hole) {
        super();
        this.pull = pull;
        this.domain = domain;
        this.labels = labels;
        this.values = values;
        this.marker = marker;
        this.textinfo = textinfo;
        this.hole = hole;
    }

    public DatumPie(String uid, Boolean showlegend,
                    String mode, String type,
                    String name, Long pull,
                    Domain domain, List<String> labels,
                    List<String> values, Marker marker,
                    String textinfo, Double hole) {
        super(uid, showlegend, mode, type, name);
        this.pull = pull;
        this.domain = domain;
        this.labels = labels;
        this.values = values;
        this.marker = marker;
        this.textinfo = textinfo;
        this.hole = hole;
    }

    public Long getPull() {
        return pull;
    }

    public void setPull(Long pull) {
        this.pull = pull;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getTextinfo() {
        return textinfo;
    }

    public void setTextinfo(String textinfo) {
        this.textinfo = textinfo;
    }

    public Double getHole() {
        return hole;
    }

    public void setHole(Double hole) {
        this.hole = hole;
    }
    /**
     * Pie Static
     */
    public static DatumPie createSimpleDataForPie(List<String> labels, Boolean showlegend,
                                                  List<String> values, String textinfo, Double hole
                                                  ) {

        DatumPie datumPie = new DatumPie();
        datumPie.setPull(Long.valueOf(0));
        datumPie.setUid("34553");
        datumPie.setDomain(Domain.creatSimpleDomain());
        datumPie.setLabels(labels);
        datumPie.setShowlegend(showlegend);
        datumPie.setTextinfo(textinfo);
        datumPie.setValues(values);
        datumPie.setHole(hole);
        datumPie.setType("pie");

        return datumPie;
    }

}
