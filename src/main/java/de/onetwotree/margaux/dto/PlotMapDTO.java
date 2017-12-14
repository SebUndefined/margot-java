package de.onetwotree.margaux.dto;

import java.math.BigDecimal;

/**
 * Created by SebUndefined on 14/12/17.
 */
public class PlotMapDTO {

    private Long projectId;
    private Long plotId;
    private Double latitude;
    private Double longitude;
    private String name;
    private BigDecimal size;


    public PlotMapDTO(Long projectId, Long plotId, Double latitude, Double longitude, String name, BigDecimal size) {
        this.projectId = projectId;
        this.plotId = plotId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.size = size;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

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
