package de.onetwotree.margaux.entity;


/**
 * Created by SebUndefined on 10/07/17.
 */
public class Plot extends MainEntity {
    private String latitude;
    private String longitude;
    private Project project;

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
