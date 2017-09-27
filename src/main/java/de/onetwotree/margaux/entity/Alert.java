package de.onetwotree.margaux.entity;


import javax.persistence.*;
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

    private String level;

    private String status;

    @OneToMany(
            mappedBy = "alert",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<AlertComment> alertComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
