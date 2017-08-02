package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.onetwotree.margaux.entity.MainEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int id;
    @Column(name = "resource_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_type_id")
    private ResourceType resourceType;

    @ManyToMany(mappedBy = "resources")
    private List<Plot> plots = new ArrayList<Plot>();

    @OneToMany(
            mappedBy = "resource",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Harvest> harvests = new ArrayList<>();
    public Resource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public List<Harvest> getHarvests() {
        return harvests;
    }

    public void setHarvests(List<Harvest> harvests) {
        this.harvests = harvests;
    }
}
