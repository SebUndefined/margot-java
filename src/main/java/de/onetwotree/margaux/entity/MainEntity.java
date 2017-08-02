package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.entityJson.MainEntityView;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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
    @JsonView(MainEntityView.Simple.class)
    protected long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
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
