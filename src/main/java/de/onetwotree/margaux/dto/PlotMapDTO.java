package de.onetwotree.margaux.dto;

import java.math.BigDecimal;

/**
 * Created by SebUndefined on 14/12/17.
 */
public class PlotMapDTO {

    private Double latitude;
    private Double longitude;
    private String name;
    private BigDecimal size;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }
}
