package de.onetwotree.margaux.entity;


import de.onetwotree.margaux.entity.MainEntity;
import de.onetwotree.margaux.entity.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_plot")
public class Plot extends MainEntity {
    @Column(name = "project_latitude")
    private String latitude;
    @Column(name = "project_longitude")
    private String longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "db_plot_resource",
            joinColumns = @JoinColumn(name = "plot_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resources = new ArrayList<Resource>();

    public Plot() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
