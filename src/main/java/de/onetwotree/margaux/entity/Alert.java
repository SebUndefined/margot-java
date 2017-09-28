package de.onetwotree.margaux.entity;


import de.onetwotree.margaux.Enum.AlertLevel;
import de.onetwotree.margaux.Enum.AlertStatus;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private AlertLevel level;
    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private String subject;
    private String comment;

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
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AlertLevel getLevel() {
        return level;
    }

    public void setLevel(AlertLevel level) {
        this.level = level;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<AlertComment> getAlertComments() {
        return alertComments;
    }

    public void setAlertComments(List<AlertComment> alertComments) {
        this.alertComments = alertComments;
    }


    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MainEntity getMainEntity() {
        return mainEntity;
    }

    public void setMainEntity(MainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }
}
