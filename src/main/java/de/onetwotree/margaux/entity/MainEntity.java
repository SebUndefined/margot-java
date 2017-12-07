package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import de.onetwotree.margaux.entityJson.MainEntityView;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_main_entity",
        uniqueConstraints = @UniqueConstraint(name="name_uc", columnNames = {"name"}))
@Inheritance(
        strategy = InheritanceType.JOINED
)
public abstract class MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_entity_id")
    @JsonView(MainEntityView.Simple.class)
    protected Long id;
    @Column(
            name = "name",
            nullable = false
    )
    @NotNull(message = "Name Cannot be null ! ")
    @NotEmpty(message = "Please enter a name")
    @Size(min = 4, max = 155, message = "Size should be between 1 and 155 characteres")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private Country country;

    @OneToMany(
            mappedBy = "mainEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Alert> alerts = new ArrayList<Alert>();

    protected MainEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
