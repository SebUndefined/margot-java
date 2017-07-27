package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.onetwotree.margaux.entity.User;

import javax.persistence.*;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_main_entity")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_entity_id")
    protected long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    protected User manager;

    protected MainEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }



}
