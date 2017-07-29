package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    @Column(name = "resource_unity")
    private String unity;

    @ManyToMany(mappedBy = "resources")
    private List<Plot> plots = new ArrayList<Plot>();
    public Resource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
}
