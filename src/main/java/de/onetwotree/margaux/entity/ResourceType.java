package de.onetwotree.margaux.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Entity
@Table(name = "db_resource_type")
public class ResourceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_type_id")
    private Long id;
    @Column(name = "resource_type_name")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_unity_id")
    private Unity unity;

    @OneToMany(
            mappedBy = "resourceType",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Resource> resources = new ArrayList<>();

    public ResourceType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unity getUnity() {
        return unity;
    }

    public void setUnity(Unity unity) {
        this.unity = unity;
    }

   public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
