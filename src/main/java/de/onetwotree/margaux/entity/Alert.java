package de.onetwotree.margaux.entity;


import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by SebUndefined on 27/09/17.
 */
@Entity
@Table(name = "db_alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long id;

    private LocalDate date;

    private String level;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_entity_id", nullable = false)
    private MainEntity mainEntity;


    @OneToMany(
            mappedBy = "alert",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<AlertComment> alertComments;

    public Alert() {
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<AlertComment> getAlertComments() {
        return alertComments;
    }

    public void setAlertComments(List<AlertComment> alertComments) {
        this.alertComments = alertComments;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MainEntity getMainEntity() {
        return mainEntity;
    }

    public void setMainEntity(MainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }
}
