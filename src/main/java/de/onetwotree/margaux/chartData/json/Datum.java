
package de.onetwotree.margaux.chartData.json;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "x",
    "y",
    "type",
        "name",
        "domain"
})
public class Datum {

    @JsonProperty("x")
    protected List<String> x = null;
    @JsonProperty("y")
    protected List<BigDecimal> y = null;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("name")
    protected String name;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Datum() {
    }
    public Datum(List<String> x, List<BigDecimal> y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public Datum(List<String> x, List<BigDecimal> y, String type, String name) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.name = name;
    }

    @JsonProperty("x")
    public List<String> getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(List<String> x) {
        this.x = x;
    }

    public Datum withX(List<String> x) {
        this.x = x;
        return this;
    }

    @JsonProperty("y")
    public List<BigDecimal> getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(List<BigDecimal> y) {
        this.y = y;
    }



    public Datum withY(List<BigDecimal> y) {
        this.y = y;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }



    public Datum withType(String type) {
        this.type = type;
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Datum withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
