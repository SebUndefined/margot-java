
package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import de.onetwotree.margaux.entity.MainEntity;
import de.onetwotree.margaux.entity.PlotResource;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entityJson.PlotView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SebUndefined on 10/07/17.
 */

@Entity
@Table(name = "db_plot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Plot extends MainEntity implements Serializable {

    @NotNull(message = "Begin date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "plot_creation_date", nullable = false)
    @JsonView(PlotView.PlotBasic.class)
    private LocalDate creationDate;
    @Column(name = "plot_size")
    @JsonView(PlotView.PlotBasic.class)
    private BigDecimal size;
    @Column(name = "plot_latitude")
    @JsonView(PlotView.PlotBasic.class)
    private double latitude;
    @Column(name = "plot_longitude")
    @JsonView(PlotView.PlotBasic.class)
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonView(PlotView.PlotWithUserAndCompany.class)
    private Project project;

    @OneToMany(mappedBy = "plot", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PlotResource> plotResources = new ArrayList<>();

    public Plot() {
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<de.onetwotree.margaux.entity.PlotResource> getPlotResources() {
        return plotResources;
    }

    public void setPlotResources(List<PlotResource> plotResources) {
        this.plotResources = plotResources;
    }

    public List<Resource> getResources() {
        List<Resource> resourceList = new ArrayList<>();
        for (PlotResource plotResource : this.getPlotResources()) {
            resourceList.add(plotResource.getResource());
        }
        return resourceList;
    }

    public boolean hasResource(Resource resource) {
        if (this.getResources().contains(resource)) {
            System.out.println("this plot has the ressource " + resource.getName());
            return true;
        }
        else {
            return false;
        }
    }
}

